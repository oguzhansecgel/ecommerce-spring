package com.shop.gateway_server.gatewayfilter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public GatewayConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("customer_service", r -> r.path("/api/v1/auth/")
                        .filters(f -> f.filter(jwtAuthenticationFilter)) // JWT filtresi burada uygulanır
                        .uri("lb://customer-service"))
                .route("product_service",r->r.path("/api/v1/product/**")
                        .filters(f->f.filter(jwtAuthenticationFilter))
                        .uri("lb://product-service"))
                .build();
    }
}
*/