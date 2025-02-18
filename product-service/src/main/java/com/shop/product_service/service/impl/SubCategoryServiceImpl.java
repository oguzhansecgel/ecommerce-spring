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
import com.shop.product_service.response.ApiResponse;
import com.shop.product_service.service.SubCategoryService;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<CreateSubCategoryResponse> createSubCategory(CreateSubCategoryRequest request) {
        try {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            SubCategory subCategory = SubCategoryMapper.toCreateSubCategoryRequest(request, category);
            SubCategory savedSubCategory = subCategoryRepository.save(subCategory);

            CreateSubCategoryResponse response = new CreateSubCategoryResponse(
                    savedSubCategory.getId(),
                    savedSubCategory.getName(),
                    savedSubCategory.getCategory().getId());

            return new ApiResponse<>(HttpStatus.OK, "SubCategory created successfully", response, null);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Failed to create SubCategory", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<UpdateSubCategoryResponse> updateSubCategory(UpdateSubCategoryRequest request, Long id) {
        try {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            SubCategory subCategory = subCategoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("SubCategory not found"));

            subCategory.setName(request.getName());
            subCategory.setCategory(category);

            SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
            UpdateSubCategoryResponse response = new UpdateSubCategoryResponse(
                    savedSubCategory.getId(),
                    savedSubCategory.getName(),
                    savedSubCategory.getCategory().getId());

            return new ApiResponse<>(HttpStatus.OK, "SubCategory updated successfully", response, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Failed to update SubCategory", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<List<GetAllSubCategoryResponse>> getAllSubCategory() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        List<GetAllSubCategoryResponse> response = SubCategoryMapper.dtoToGetAllSubCategoryResponse(subCategories);
        return new ApiResponse<>(HttpStatus.OK, "All subcategories fetched successfully", response, null);
    }

    @Override
    public ApiResponse<GetByIdSubCategoryResponse> getSubCategoryById(Long id) {
        try {
            SubCategory subCategory = subCategoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("SubCategory not found"));

            GetByIdSubCategoryResponse response = SubCategoryMapper.dtoGetByIdSubCategoryResponse(subCategory);
            return new ApiResponse<>(HttpStatus.OK, "SubCategory found", response, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "SubCategory not found", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<Void> deleteSubCategory(Long id) {
        try {
            SubCategory subCategory = subCategoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("SubCategory not found"));

            subCategoryRepository.delete(subCategory);
            return new ApiResponse<>(HttpStatus.OK, "SubCategory deleted successfully", null, null);
        } catch (RuntimeException e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Failed to delete SubCategory", null, List.of(e.getMessage()));
        }
    }

    @Override
    public ApiResponse<List<GetAllSubCategoryResponse>> subCategoryWithCategory(Long categoryId) {
        try {
            List<SubCategory> subCategories = subCategoryRepository.findSubCategoriesByCategory_Id(categoryId);
            List<GetAllSubCategoryResponse> response = SubCategoryMapper.dtoToGetAllSubCategoryResponse(subCategories);
            return new ApiResponse<>(HttpStatus.OK, "SubCategories with category fetched successfully", response, null);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Failed to fetch subcategories with category", null, List.of(e.getMessage()));
        }
    }

}
