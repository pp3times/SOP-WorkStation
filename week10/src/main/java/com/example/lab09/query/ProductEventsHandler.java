package com.example.lab09.query;

import com.example.lab09.core.ProductEntity;
import com.example.lab09.core.data.ProductRepository;
import com.example.lab09.core.event.ProductCreatedEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productRepository.save(productEntity);
    }
}
