package com.shop.search_service.rabbitmq;

import com.shop.search_service.model.Product;
import com.shop.search_service.repository.SearchServiceRepository;
import events.product.CreateProductEvent;
import events.product.UpdateProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProductListener {

    private final SearchServiceRepository searchServiceRepository;
    private Logger log = LoggerFactory.getLogger(ProductListener.class);
    public ProductListener(SearchServiceRepository searchServiceRepository) {
        this.searchServiceRepository = searchServiceRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void process(CreateProductEvent event)
    {
        log.info("Product Created Successfully");
        Product product = new Product();
        product.setId(event.getId());
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        searchServiceRepository.save(product);
    }

    @RabbitListener(queues = "${rabbitmq.update.queue.name}")
    public void process(UpdateProductEvent event)
    {
        log.info("Product Update Successfully");
        Product product = new Product();
        product.setId(event.getId());
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        searchServiceRepository.save(product);
    }
}
