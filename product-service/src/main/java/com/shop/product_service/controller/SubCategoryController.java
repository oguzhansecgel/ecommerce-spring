package com.shop.product_service.controller;

import com.shop.product_service.dto.request.subcategory.CreateSubCategoryRequest;
import com.shop.product_service.dto.request.subcategory.UpdateSubCategoryRequest;
import com.shop.product_service.dto.response.subcategory.CreateSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetAllSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetByIdSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.UpdateSubCategoryResponse;
import com.shop.product_service.service.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subCategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }
    @GetMapping("/get/by/{id}/subCategory")
    public GetByIdSubCategoryResponse getById(@PathVariable Long id)
    {
        return subCategoryService.getSubCategoryById(id);
    }
    @GetMapping("/get/all/subCategory")
    public List<GetAllSubCategoryResponse> getAllSubCategory()
    {
        return subCategoryService.getAllSubCategory();
    }
    @PostMapping("/create/subCategory")
    public CreateSubCategoryResponse createSubCategory(@RequestBody CreateSubCategoryRequest request)
    {
        return subCategoryService.createSubCategory(request);
    }
    @PutMapping("/update/subCategory/{id}")
    public UpdateSubCategoryResponse updateSubCategory(@PathVariable Long id, @RequestBody UpdateSubCategoryRequest request)
    {
        return subCategoryService.updateSubCategory(request, id);
    }
    @DeleteMapping("/delete/subCategory/{id}")
    public void deleteSubCategory(@PathVariable Long id)
    {
        subCategoryService.deleteSubCategory(id);
    }
}
