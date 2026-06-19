package com.prueba.crud.mappers;

import com.prueba.crud.entities.UserModel;
import com.prueba.crud.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserResponse toResponse(UserModel user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole().name())
                .build();
    }

    public List<UserResponse> toResponseList(List<UserModel> users) {
        return users.stream().map(this::toResponse).toList();
    }
}