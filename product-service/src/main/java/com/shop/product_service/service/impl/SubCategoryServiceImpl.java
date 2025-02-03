package com.shop.product_service.service.impl;

import com.shop.product_service.dto.request.subcategory.CreateSubCategoryRequest;
import com.shop.product_service.dto.request.subcategory.UpdateSubCategoryRequest;
import com.shop.product_service.dto.response.subcategory.CreateSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetAllSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetByIdSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.UpdateSubCategoryResponse;
import com.shop.product_service.entity.Category;
import com.shop.product_service.entity.SubCategory;
import com.shop.product_service.mapper.CategoryMapper;
import com.shop.product_service.mapper.SubCategoryMapper;
import com.shop.product_service.repository.CategoryRepository;
import com.shop.product_service.repository.SubCategoryRepository;
import com.shop.product_service.service.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CreateSubCategoryResponse createSubCategory(CreateSubCategoryRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        SubCategory subCategory = SubCategoryMapper.toCreateSubCategoryRequest(request, category);

        SubCategory savedSubCategory = subCategoryRepository.save(subCategory);

        return new CreateSubCategoryResponse(savedSubCategory.getId(),
                savedSubCategory.getName(),
                savedSubCategory.getCategory().getId());
    }

    @Override
    public UpdateSubCategoryResponse updateSubCategory(UpdateSubCategoryRequest request, Long id) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategory subCategory = subCategoryRepository.findById(id).get();
        subCategory.setName(request.getName());
        subCategory.setCategory(category);
        SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
        return new UpdateSubCategoryResponse(savedSubCategory.getId(), savedSubCategory.getName(), savedSubCategory.getCategory().getId());
    }

    @Override
    public List<GetAllSubCategoryResponse> getAllSubCategory() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        return SubCategoryMapper.dtoToGetAllSubCategoryResponse(subCategories);
    }

    @Override
    public GetByIdSubCategoryResponse getSubCategoryById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).get();
        return SubCategoryMapper.dtoGetByIdSubCategoryResponse(subCategory);
    }

    @Override
    public void deleteSubCategory(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).get();
        subCategoryRepository.delete(subCategory);
    }
}
