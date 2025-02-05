package com.shop.search_service.repository;

import com.shop.search_service.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchServiceRepository extends ElasticsearchRepository<Product,String> {

}
