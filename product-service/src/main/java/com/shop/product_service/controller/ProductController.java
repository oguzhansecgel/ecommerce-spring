package com.shop.product_service.controller;

import com.shop.product_service.dto.request.product.CreateProductRequest;
import com.shop.product_service.dto.request.product.UpdateProductRequest;
import com.shop.product_service.dto.response.product.CreateProductResponse;
import com.shop.product_service.dto.response.product.GetAllProductResponse;
import com.shop.product_service.dto.response.product.GetByIdProductResponse;
import com.shop.product_service.dto.response.product.UpdateProductResponse;
import com.shop.product_service.response.ApiResponse;
import com.shop.product_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/by/{id}/product")
    public ResponseEntity<ApiResponse<GetByIdProductResponse>> getProductById(@PathVariable Long id) {
        ApiResponse<GetByIdProductResponse> response =  productService.getProductById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/get/all/products")
    public ApiResponse<List<GetAllProductResponse>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/all/subcategory/{id}")
    public ApiResponse<List<GetAllProductResponse>> productsBySubCategory(@PathVariable Long id) {
        return productService.productWithSubCategory(id);
    }

    @PostMapping("/create/product")
    public ApiResponse<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/update/product/{id}")
    public ApiResponse<UpdateProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/delete/product/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
