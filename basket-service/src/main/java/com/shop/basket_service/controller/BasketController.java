package com.shop.basket_service.controller;

import com.shop.basket_service.model.Basket;
import com.shop.basket_service.service.BasketService;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }
    @GetMapping("/find/basket/by/customer/{customerId}")
    public Basket findByBasketCustomerId(@PathVariable("customerId") String customerId)
    {
        return basketService.findBasketByCustomerId(customerId);
    }
    @GetMapping("/find/by/basket/{basketId}")
    public Optional<Basket> findByBasket(@PathVariable String basketId)
    {
        return basketService.getByIdBasket(basketId);
    }
    @PostMapping("/remove/items")
    public ResponseEntity<Basket> removeItemsFromBasket(
            @RequestHeader("X-UserId") String customerId,
            @RequestBody Map<Long, Integer> productQuantities) {

        Basket updatedBasket = basketService.removeFromBasket(customerId, productQuantities);
        return ResponseEntity.ok(updatedBasket);
    }
    @PostMapping("/create/basket")
    public ResponseEntity<Basket> createBasketItem(@RequestHeader("X-UserId") String userId, @RequestBody Map<Long, Integer> productQuantities) {
        System.out.println("USERID"+userId);
        Basket basket = basketService.createBasket(userId, productQuantities);
        return new ResponseEntity<>(basket, HttpStatus.CREATED);
    }
}
