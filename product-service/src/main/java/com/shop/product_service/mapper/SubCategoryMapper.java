package com.shop.product_service.mapper;

import com.shop.product_service.dto.request.subcategory.CreateSubCategoryRequest;
import com.shop.product_service.dto.request.subcategory.UpdateSubCategoryRequest;
import com.shop.product_service.dto.response.subcategory.CreateSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetAllSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.GetByIdSubCategoryResponse;
import com.shop.product_service.dto.response.subcategory.UpdateSubCategoryResponse;
import com.shop.product_service.entity.Category;
import com.shop.product_service.entity.SubCategory;

import java.util.List;
import java.util.stream.Collectors;

public class SubCategoryMapper {

    public static SubCategory toCreateSubCategoryRequest(CreateSubCategoryRequest request, Category category) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.getName());
        subCategory.setCategory(category);
        return subCategory;
    }
    public static SubCategory toUpdateSubCategoryRequest(UpdateSubCategoryRequest request, Category category)
    {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.getName());
        subCategory.setCategory(category);
        return subCategory;
    }

    public static CreateSubCategoryResponse dtoToCreateSubCategoryResponse(SubCategory from)
    {
        return new CreateSubCategoryResponse(from.getId(), from.getName(), from.getCategory().getId());
    }
    public static UpdateSubCategoryResponse dtoToUpdateSubCategoryResponse(SubCategory from)
    {
        return new UpdateSubCategoryResponse(from.getId(), from.getName(), from.getCategory().getId());
    }
    public static GetAllSubCategoryResponse toDto(SubCategory subCategory) {
        return new GetAllSubCategoryResponse(
                subCategory.getId(),
                subCategory.getName(),
                subCategory.getCategory().getId()
        );
    }
    public static List<GetAllSubCategoryResponse> dtoToGetAllSubCategoryResponse(List<SubCategory> subCategories) {
        return subCategories.stream()
                .map(SubCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public static GetByIdSubCategoryResponse dtoGetByIdSubCategoryResponse(SubCategory subCategory) {
        return new GetByIdSubCategoryResponse(subCategory.getId(), subCategory.getName(),subCategory.getCategory().getId());
    }

}
