package com.shop.product_service.controller;

import com.shop.product_service.dto.request.attribute.CreateAttributeRequest;
import com.shop.product_service.dto.request.attribute.UpdateAttributeRequest;
import com.shop.product_service.dto.response.attribute.*;
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
    public ResponseEntity<CreateAttributeResponse> createAttribute(@RequestBody CreateAttributeRequest request) {
        CreateAttributeResponse response = attributeService.createAttribute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<GetByIdAttributeResponse> getAttributeById(@PathVariable Long id) {
        GetByIdAttributeResponse response = attributeService.getAttributeById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get/all/attribute")
    public List<GetAllAttributeResponse> getAllAttribute()
    {
        return attributeService.getAllAttributes();
    }
    @GetMapping("/attribute/with/product/{productId}")
    public GetAttributeWithProductResponse getAttributeWithProduct(@PathVariable Long productId)
    {
        return attributeService.getAttributeWithProduct(productId);
    }
    // Attribute güncelleme
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateAttributeResponse> updateAttribute(@PathVariable Long id, @RequestBody UpdateAttributeRequest request) {
        UpdateAttributeResponse response = attributeService.updateAttribute(id, request);
        return ResponseEntity.ok(response);
    }

    // Attribute silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttribute(@PathVariable Long id) {
        attributeService.deleteAttribute(id);
        return ResponseEntity.noContent().build();
    }
}
