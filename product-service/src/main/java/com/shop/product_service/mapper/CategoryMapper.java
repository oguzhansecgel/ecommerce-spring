package com.shop.product_service.mapper;

import com.shop.product_service.dto.request.category.CreateCategoryRequest;
import com.shop.product_service.dto.request.category.UpdateCategoryRequest;
import com.shop.product_service.dto.response.category.CreateCategoryResponse;
import com.shop.product_service.dto.response.category.GetAllCategoryResponse;
import com.shop.product_service.dto.response.category.GetByIdCategoryResponse;
import com.shop.product_service.dto.response.category.UpdateCategoryResponse;
import com.shop.product_service.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {
    public static Category dtoToCreateCategory(CreateCategoryRequest dto)
    {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
    public static Category dtoToUpdateCategory(UpdateCategoryRequest dto)
    {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public static CreateCategoryResponse dtoToCreateCategoryResponse(Category from)
    {
        return new CreateCategoryResponse(from.getId(), from.getName());
    }
    public static UpdateCategoryResponse dtoToUpdateCategoryResponse(Category from)
    {
        return new UpdateCategoryResponse(from.getId(), from.getName());
    }

    public static GetAllCategoryResponse toDto(Category category) {
        return new GetAllCategoryResponse(
                category.getId(),
                category.getName()
        );
    }

    public static List<GetAllCategoryResponse> dtToGetAllCategoryResponse(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public static GetByIdCategoryResponse dtoGetByIdCategoryResponse(Category category) {
        return new GetByIdCategoryResponse(category.getId(), category.getName());
    }
}
