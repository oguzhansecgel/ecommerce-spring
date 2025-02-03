package com.shop.product_service.service.impl;

import com.shop.product_service.dto.request.category.CreateCategoryRequest;
import com.shop.product_service.dto.request.category.UpdateCategoryRequest;
import com.shop.product_service.dto.response.category.CreateCategoryResponse;
import com.shop.product_service.dto.response.category.GetAllCategoryResponse;
import com.shop.product_service.dto.response.category.GetByIdCategoryResponse;
import com.shop.product_service.dto.response.category.UpdateCategoryResponse;
import com.shop.product_service.entity.Category;
import com.shop.product_service.mapper.CategoryMapper;
import com.shop.product_service.repository.CategoryRepository;
import com.shop.product_service.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = CategoryMapper.dtoToCreateCategory(request);
        Category savedCategory = repository.save(category);
        return CategoryMapper.dtoToCreateCategoryResponse(savedCategory);
    }

    @Override
    public UpdateCategoryResponse updateCategory(UpdateCategoryRequest request, Long id) {
        Category category = repository.findById(id).get();
        category.setName(request.getName());
        Category savedCategory = repository.save(category);
        return CategoryMapper.dtoToUpdateCategoryResponse(savedCategory);
    }

    @Override
    public List<GetAllCategoryResponse> getAllCategory() {
        List<Category> category = repository.findAll();
        return CategoryMapper.dtToGetAllCategoryResponse(category);
    }

    @Override
    public GetByIdCategoryResponse getCategoryById(Long id) {
        Category category = repository.findById(id).get();
        return CategoryMapper.dtoGetByIdCategoryResponse(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = repository.findById(id).get();
        repository.delete(category);
    }
}
