package com.shop.search_service.controller;

import com.shop.search_service.model.Product;
import com.shop.search_service.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping("/get/all/products")
    public Iterable<Product> getAllProduct()
    {
        return searchService.search();
    }
}
