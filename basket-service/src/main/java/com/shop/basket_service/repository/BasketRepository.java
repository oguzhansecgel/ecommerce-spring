package com.shop.basket_service.repository;

import com.shop.basket_service.model.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends MongoRepository<Basket, String> {
    Basket findByCustomerId(int customerId);
}
