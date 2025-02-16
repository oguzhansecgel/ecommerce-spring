package com.shop.gateway_server.gatewayfilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Predicate;


@Component
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtService jwtService;
    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("Filter çalışıyor.");

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        logger.info("Requested Path: {}", path);
        logger.info("Is Public Endpoint: {}", isPublicEndpoint(path));

        return validateToken(exchange, chain, path);
    }

    private Mono<Void> validateToken(ServerWebExchange exchange, WebFilterChain chain, String path) {
        if (!isAuthenticatedEndpoint(path)) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange);
        if (token == null) {
            return handleUnauthorizedResponse(exchange, "Authorization header eksik. Lütfen token sağlayın.");

        }
        try {
            jwtService.validateToken(token);

            String username = jwtService.extractUsername(token);
            List<String> roles = jwtService.extractRoles(token);
            Integer userId = jwtService.extractUserIdFromToken(token); // userId'yi alıyoruz

            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                    .header("X-UserId", userId.toString())
                    .build();
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, AuthorityUtils.createAuthorityList(roles.toArray(new String[0]))
            );

            SecurityContext context = new SecurityContextImpl(authentication);

            return chain.filter(exchange.mutate().request(modifiedRequest).build())
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));

        } catch (ExpiredJwtException e) {
            return handleUnauthorizedResponse(exchange, "Oturum süreniz doldu. Lütfen tekrar giriş yapın.");
        } catch (JwtException e) {
            return handleUnauthorizedResponse(exchange, "Geçersiz token. Lütfen tekrar giriş yapın.");
        } catch (Exception e) {
            e.printStackTrace();  // hatayı detaylı bir şekilde konsola yazdırın
            logger.error("Hata: ", e);  // log ile hatayı kaydedin
            return handleUnauthorizedResponse(exchange, "******************************");
        }
    }

    private Mono<Void> handleUnauthorizedResponse(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean isPublicEndpoint(String path) {
        return RouterValidator.openApiEndpoints.stream()
                .anyMatch(endpoint -> path.matches(endpoint.replace("**", ".*")));
    }

    private boolean isAuthenticatedEndpoint(String path) {
        return RouterValidator.authenticatedApiEndpoints.stream()
                .anyMatch(endpoint -> path.matches(endpoint.replace("**", ".*")));
    }

}


/*
@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    private final JwtService jwtService;
    private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("***************************************************************************** filter çalışıyor .");

        // JWT token'ı Header'dan alıyoruz
        String token = extractToken(exchange);

        if (token != null) {
            System.out.println("***************************************************************************** token null değil .");
            try {
                jwtService.validateToken(token);

                String username = jwtService.extractUsername(token);
                List<String> roles = jwtService.extractRoles(token);
                System.out.println("**************** filter içerisinden geliyorum : "+roles);
                // Kullanıcıyı ve rollerini SecurityContext'e ekliyoruz
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                username, null, AuthorityUtils.createAuthorityList(roles.toArray(new String[0]))
                        )
                );
            } catch (Exception e) {
                System.out.println("***************************************************************************** catche düştü.");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        } else {
            System.out.println("***************************************************************************** token null .");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            log.debug("****************************************************************************** Extract Token");
            return authHeader.substring(7); // "Bearer " kısmını çıkarıyoruz
        }
        log.debug("****************************************************************************** Extract Token NULL");
        return null;
    }
}

*/
