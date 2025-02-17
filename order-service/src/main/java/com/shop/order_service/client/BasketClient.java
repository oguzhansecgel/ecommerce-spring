package com.shop.order_service.client;

import com.shop.order_service.model.Basket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "basket-service")
public interface BasketClient {

    @GetMapping("/api/v1/basket/find/by/basket/{basketId}")
    Basket findByBasketId(@PathVariable("basketId") String basketId);
}
