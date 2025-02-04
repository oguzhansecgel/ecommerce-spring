package com.shop.product_service.mapper;

import com.shop.product_service.dto.request.product.CreateProductRequest;
import com.shop.product_service.dto.request.product.UpdateProductRequest;
import com.shop.product_service.dto.response.product.CreateProductResponse;
import com.shop.product_service.dto.response.product.GetAllProductResponse;
import com.shop.product_service.dto.response.product.GetByIdProductResponse;
import com.shop.product_service.dto.response.product.UpdateProductResponse;
import com.shop.product_service.entity.Product;
import com.shop.product_service.entity.SubCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

        public static Product dtoToCreateProduct(CreateProductRequest request, SubCategory subCategory) {
            Product product = new Product();
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setDescription(request.getDescription());
            product.setStock(request.getStock());
            product.setSubCategory(subCategory);
            return product;
        }

        public static Product dtoToUpdateProduct(UpdateProductRequest request, SubCategory subCategory) {
            Product product = new Product();
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setDescription(request.getDescription());
            product.setStock(request.getStock());
            product.setSubCategory(subCategory);
            return product;
        }

        public static CreateProductResponse dtoToCreateProductResponse(Product product) {
            return new CreateProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getStock(),
                    product.getSubCategory().getId()
            );
        }

        public static UpdateProductResponse dtoToUpdateProductResponse(Product product) {
            return new UpdateProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getStock(),
                    product.getSubCategory().getId()
            );
        }

        public static GetAllProductResponse toDto(Product product) {
            return new GetAllProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getStock(),
                    product.getSubCategory().getId()
            );
        }

        public static List<GetAllProductResponse> dtoToGetAllProductsResponse(List<Product> products) {
            return products.stream()
                    .map(ProductMapper::toDto)
                    .collect(Collectors.toList());
        }

        public static GetByIdProductResponse dtoGetByIdProductResponse(Product product) {
            return new GetByIdProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getStock(),
                    product.getSubCategory().getId()
            );
        }

}
