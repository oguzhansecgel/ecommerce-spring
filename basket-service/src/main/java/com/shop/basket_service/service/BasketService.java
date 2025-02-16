package com.shop.basket_service.service;

import com.shop.basket_service.client.ProductClient;
import com.shop.basket_service.model.Basket;
import com.shop.basket_service.model.BasketItem;
import com.shop.basket_service.model.ProductClientDto;
import com.shop.basket_service.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductClient productClient;

    public BasketService(BasketRepository basketRepository, ProductClient productClient) {
        this.basketRepository = basketRepository;
        this.productClient = productClient;
    }
    public Basket findBasketByCustomerId(String customerId)
    {
        return basketRepository.findBasketByCustomerId(customerId);
    }
    public Basket createBasket(String customerId, Map<Long, Integer> productQuantities) {
        Basket basket = basketRepository.findByCustomerId(customerId);

        if (basket == null) {
            basket = new Basket();
            basket.setCustomerId(customerId);
            basket.setBasketItems(new ArrayList<>());
            basket.setTotalPrice(BigDecimal.ZERO);
        }

        List<BasketItem> items = basket.getBasketItems();
        BigDecimal totalPrice = basket.getTotalPrice();

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue() != null ? entry.getValue() : 1;

            Optional<ProductClientDto> optionalProduct = productClient.getProductById(productId);
            if (optionalProduct.isEmpty()) {
                throw new RuntimeException("Product not found");
            }
            ProductClientDto productDto = optionalProduct.get();

            BasketItem existingItem = findBasketItemByProductId(items, productId);

            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                existingItem.setTotalPrice(existingItem.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(existingItem.getQuantity())));
            } else {
                BasketItem newItem = new BasketItem();
                newItem.setProduct(productDto);
                newItem.setQuantity(quantity);
                newItem.setTotalPrice(productDto.getPrice().multiply(BigDecimal.valueOf(quantity)));
                items.add(newItem);
            }

            totalPrice = totalPrice.add(productDto.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }

        basket.setBasketItems(items);
        basket.setTotalPrice(totalPrice);

        return basketRepository.save(basket);
    }

    private BasketItem findBasketItemByProductId(List<BasketItem> items, Long productId) {
        for (BasketItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

}
