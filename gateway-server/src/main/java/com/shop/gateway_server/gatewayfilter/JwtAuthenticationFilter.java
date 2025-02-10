package com.shop.gateway_server.gatewayfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

        // Eğer izin verilen bir endpoint ise, JWT kontrolünü atlıyoruz
        if (!isAuthenticatedEndpoint(path)) {
            logger.info("****************************** Bypassing authentication for public endpoint");
            return chain.filter(exchange);
        }

        // JWT token'ı Header'dan alıyoruz
        String token = extractToken(exchange);

        if (token != null) {
            try {
                jwtService.validateToken(token);

                String username = jwtService.extractUsername(token);
                List<String> roles = jwtService.extractRoles(token);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username, null, AuthorityUtils.createAuthorityList(roles.toArray(new String[0]))
                );

                SecurityContext context = new SecurityContextImpl(authentication);

                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));

            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // "Bearer " kısmını çıkarıyoruz
        }
        return null;
    }

    // Belirtilen endpoint'in izin verilen listede olup olmadığını kontrol ediyoruz
    private boolean isPublicEndpoint(String path) {
        System.out.println("***************************** PATH: "+path);
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
