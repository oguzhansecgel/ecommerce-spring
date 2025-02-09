package com.shop.gateway_server.gatewayfilter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    // doğrulama istenilen endpointler
    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/product/get/all/products",
            "/api/v1/subCategory/get/all/subCategory"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));
}