package com.prueba.crud.mappers;

import com.prueba.crud.entities.ProductModel;
import com.prueba.crud.product.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductResponse toResponse(ProductModel product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }

    public List<ProductResponse> toResponseList(List<ProductModel> products) {
        return products.stream().map(this::toResponse).toList();
    }
}