package com.shop.search_service.service;

import com.shop.search_service.model.Product;
import com.shop.search_service.repository.SearchServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {


    private final SearchServiceRepository repository;

    public SearchService(SearchServiceRepository repository) {
        this.repository = repository;
    }

    public Iterable<Product> search()
    {
        Iterable<Product> products =repository.findAll();
        return products;
    }
}
