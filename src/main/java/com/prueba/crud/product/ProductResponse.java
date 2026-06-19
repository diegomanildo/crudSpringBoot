package com.prueba.crud.product;

import lombok.Builder;

@Builder
public record ProductResponse(
        Long id,
        String name,
        Integer quantity
) {}