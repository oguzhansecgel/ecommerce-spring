package com.shop.product_service.service.impl;

import com.shop.product_service.dto.request.product.CreateProductRequest;
import com.shop.product_service.dto.request.product.UpdateProductRequest;
import com.shop.product_service.dto.response.product.CreateProductResponse;
import com.shop.product_service.dto.response.product.GetAllProductResponse;
import com.shop.product_service.dto.response.product.GetByIdProductResponse;
import com.shop.product_service.dto.response.product.UpdateProductResponse;
import com.shop.product_service.entity.Product;
import com.shop.product_service.entity.SubCategory;
import com.shop.product_service.mapper.ProductMapper;
import com.shop.product_service.rabbitmq.SearchServicePublisher;
import com.shop.product_service.repository.ProductRepository;
import com.shop.product_service.repository.SubCategoryRepository;
import com.shop.product_service.service.ProductService;
import events.product.CreateProductEvent;
import events.product.UpdateProductEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final SearchServicePublisher searchServicePublisher;

    public ProductServiceImpl(ProductRepository productRepository, SubCategoryRepository subCategoryRepository, SearchServicePublisher searchServicePublisher) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.searchServicePublisher = searchServicePublisher;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        SubCategory subCategory = subCategoryRepository.findById(request.getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("SubCategory not found"));

        Product product = ProductMapper.dtoToCreateProduct(request, subCategory);
        Product savedProduct = productRepository.save(product);
        CreateProductEvent event = new CreateProductEvent();
        event.setId(product.getId().toString());
        event.setName(savedProduct.getName());
        event.setPrice(savedProduct.getPrice());
        searchServicePublisher.addProductToSearchService(event);
        return ProductMapper.dtoToCreateProductResponse(savedProduct);
    }

    @Override
    public UpdateProductResponse updateProduct(Long productId, UpdateProductRequest request) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        SubCategory subCategory = subCategoryRepository.findById(request.getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("SubCategory not found"));

        Product updatedProduct = ProductMapper.dtoToUpdateProduct(request, subCategory);
        updatedProduct.setId(existingProduct.getId());

        Product savedProduct = productRepository.save(updatedProduct);
        UpdateProductEvent event = new UpdateProductEvent();
        event.setId(productId.toString());
        event.setName(savedProduct.getName());
        event.setPrice(savedProduct.getPrice());
        searchServicePublisher.updateProductToSearchService(event);
        return ProductMapper.dtoToUpdateProductResponse(savedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        searchServicePublisher.deleteProductToSearchService(productId);
        productRepository.delete(existingProduct);
    }

    @Override
    public GetByIdProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductMapper.dtoGetByIdProductResponse(product);
    }

    @Override
    public List<GetAllProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return ProductMapper.dtoToGetAllProductsResponse(products);
    }

    @Override
    public List<GetAllProductResponse> productWithSubCategory(Long subCategoryId) {
        List<Product> product = productRepository.findAllBySubCategoryId(subCategoryId);
        return ProductMapper.dtoToGetAllProductsResponse(product);
    }
}
