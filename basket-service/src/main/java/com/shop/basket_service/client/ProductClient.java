package com.shop.basket_service.client;

import com.shop.basket_service.model.ProductClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/v1/product/get/by/{id}/product")
    Optional<ProductClientDto> getProductById(@PathVariable Long id);
}
