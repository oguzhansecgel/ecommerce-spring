package com.shop.product_service.rabbitmq;

import events.product.CreateProductEvent;
import events.product.UpdateProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchServicePublisher {

    private final RabbitTemplate rabbitTemplate;
    private Logger log = LoggerFactory.getLogger(SearchServicePublisher.class);

    @Value("${rabbitmq.queue.name}")
    private String createQueue;
    @Value("${rabbitmq.update.queue.name}")
    private String updateQueue;

    public SearchServicePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addProductToSearchService(CreateProductEvent event)
    {
        log.info("Product created", event);
        rabbitTemplate.convertAndSend(createQueue, event);
    }
    public void updateProductToSearchService(UpdateProductEvent event)
    {
        log.info("Product update", event);
        rabbitTemplate.convertAndSend(updateQueue,event);
    }
}
