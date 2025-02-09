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
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/auth/refresh",
            "/api/v1/product/get/all/products"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> {
                boolean isSecured = openApiEndpoints
                        .stream()
                        .noneMatch(uri -> request.getURI().getPath().contains(uri));
                logger.info("İstek Yolu: {}, Korunmalı mı: {}", request.getURI().getPath(), isSecured); // Log Ekle
                return isSecured;
            };
}