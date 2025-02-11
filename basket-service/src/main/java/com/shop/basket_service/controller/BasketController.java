package com.shop.basket_service.controller;

import com.shop.basket_service.model.Basket;
import com.shop.basket_service.service.BasketService;
import com.shop.basket_service.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketController {

    private final BasketService basketService;
    private final JwtService jwtService;

    public BasketController(BasketService basketService, JwtService jwtService) {
        this.basketService = basketService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create/basket")
    public ResponseEntity<Basket> createBasketItem(@RequestHeader("Authorization") String token, @RequestBody Map<Long, Integer> productQuantities) {
        String tokenTrim = token.trim();
        String substring = tokenTrim.substring(7);
        Integer customerId = jwtService.extractUserIdFromToken(substring);
        Basket basket = basketService.createBasket(customerId, productQuantities);
        return new ResponseEntity<>(basket, HttpStatus.CREATED);
    }
}
