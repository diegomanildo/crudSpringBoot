package com.prueba.crud.user;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String role) {
    
}