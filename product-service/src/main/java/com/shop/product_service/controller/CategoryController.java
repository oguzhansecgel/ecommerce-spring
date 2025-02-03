package com.shop.product_service.controller;

import com.shop.product_service.dto.request.category.CreateCategoryRequest;
import com.shop.product_service.dto.request.category.UpdateCategoryRequest;
import com.shop.product_service.dto.response.category.CreateCategoryResponse;
import com.shop.product_service.dto.response.category.GetAllCategoryResponse;
import com.shop.product_service.dto.response.category.GetByIdCategoryResponse;
import com.shop.product_service.dto.response.category.UpdateCategoryResponse;
import com.shop.product_service.entity.Category;
import com.shop.product_service.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/get/all/categories")
    public List<GetAllCategoryResponse> getAllCategory()
    {
        return categoryService.getAllCategory();
    }
    @GetMapping("/get/by/{id}/category")
    public GetByIdCategoryResponse getByIdCategory(@PathVariable Long id)
    {
        return categoryService.getCategoryById(id);
    }
    @DeleteMapping("/delete/category/{id}")
    public void deleteCategory(@PathVariable Long id)
    {
        categoryService.deleteCategory(id);
    }
    @PostMapping("/create/category")
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest request)
    {
        return categoryService.createCategory(request);
    }
    @PutMapping("/update/category/{id}")
    public UpdateCategoryResponse updateCategory(@RequestBody UpdateCategoryRequest request,@PathVariable Long id)
    {
        return categoryService.updateCategory(request, id);
    }

}
