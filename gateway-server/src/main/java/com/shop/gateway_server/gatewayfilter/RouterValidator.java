package com.shop.gateway_server.gatewayfilter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/auth/**",   // Auth endpoints
            "/api/v1/public/**",
            "/api/v1/product/**",
            "/api/v1/basket/**"
    );
    public static final List<String> authenticatedApiEndpoints = List.of(
            "/api/v1/product/get/all/subcategory/\\d+",
            "/api/v1/order/**"
    );


}