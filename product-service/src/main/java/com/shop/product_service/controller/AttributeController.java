package com.shop.product_service.controller;

import com.shop.product_service.dto.request.attribute.CreateAttributeRequest;
import com.shop.product_service.dto.request.attribute.UpdateAttributeRequest;
import com.shop.product_service.dto.response.attribute.*;
import com.shop.product_service.response.ApiResponse;
import com.shop.product_service.service.AttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attribute")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateAttributeResponse>> createAttribute(@RequestBody CreateAttributeRequest request) {
        ApiResponse<CreateAttributeResponse> response = attributeService.createAttribute(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<GetByIdAttributeResponse>> getAttributeById(@PathVariable Long id) {
        ApiResponse<GetByIdAttributeResponse> response = attributeService.getAttributeById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get/all/attribute")
    public ResponseEntity<ApiResponse<List<GetAllAttributeResponse>>> getAllAttributes() {
        ApiResponse<List<GetAllAttributeResponse>> response = attributeService.getAllAttributes();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/attribute/with/product/{productId}")
    public ResponseEntity<ApiResponse<GetAttributeWithProductResponse>> getAttributeWithProduct(@PathVariable Long productId) {
        ApiResponse<GetAttributeWithProductResponse> response = attributeService.getAttributeWithProduct(productId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UpdateAttributeResponse>> updateAttribute(@PathVariable Long id, @RequestBody UpdateAttributeRequest request) {
        ApiResponse<UpdateAttributeResponse> response = attributeService.updateAttribute(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAttribute(@PathVariable Long id) {
        ApiResponse<Void> response = attributeService.deleteAttribute(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
