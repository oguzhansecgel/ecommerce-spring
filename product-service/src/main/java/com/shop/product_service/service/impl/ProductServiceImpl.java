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
import com.shop.product_service.response.ApiResponse;
import com.shop.product_service.service.ProductService;
import events.product.CreateProductEvent;
import events.product.UpdateProductEvent;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<CreateProductResponse> createProduct(CreateProductRequest request) {
        try {
            SubCategory subCategory = subCategoryRepository.findById(request.getSubCategoryId())
                    .orElseThrow(() -> new RuntimeException("SubCategory not found"));

            Product product = ProductMapper.dtoToCreateProduct(request, subCategory);
            Product savedProduct = productRepository.save(product);
            CreateProductEvent event = new CreateProductEvent();
            event.setId(product.getId().toString());
            event.setName(savedProduct.getName());
            event.setPrice(savedProduct.getPrice());
            searchServicePublisher.addProductToSearchService(event);

            CreateProductResponse response = ProductMapper.dtoToCreateProductResponse(savedProduct);
            return new ApiResponse<>(HttpStatus.CREATED, "Product created successfully", response, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Failed to create product", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<UpdateProductResponse> updateProduct(Long productId, UpdateProductRequest request) {
        try {
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

            UpdateProductResponse response = ProductMapper.dtoToUpdateProductResponse(savedProduct);
            return new ApiResponse<>(HttpStatus.OK, "Product updated successfully", response, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Failed to update product", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<Void> deleteProduct(Long productId) {
        try {
            Product existingProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            searchServicePublisher.deleteProductToSearchService(productId);
            productRepository.delete(existingProduct);

            return new ApiResponse<>(HttpStatus.NO_CONTENT, "Product deleted successfully", null, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Failed to delete product", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<GetByIdProductResponse> getProductById(Long productId) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            GetByIdProductResponse response = ProductMapper.dtoGetByIdProductResponse(product);
            return new ApiResponse<>(HttpStatus.OK, "Product found", response, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Product not found", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<List<GetAllProductResponse>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GetAllProductResponse> response = ProductMapper.dtoToGetAllProductsResponse(products);
        return new ApiResponse<>(HttpStatus.OK, "All products fetched successfully", response, null);
    }

    @Override
    public ApiResponse<List<GetAllProductResponse>> productWithSubCategory(Long subCategoryId) {
        List<Product> products = productRepository.findAllBySubCategoryId(subCategoryId);

        if (products.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "No products found for the given subcategory", null, List.of("No products found for the given subcategory"));
        }

        return new ApiResponse<>(HttpStatus.OK, "Products by SubCategory fetched successfully", ProductMapper.dtoToGetAllProductsResponse(products), null);
    }


}
