package com.prueba.crud.servicies;

import com.prueba.crud.entities.UserModel;
import com.prueba.crud.exceptions.ResourceNotFoundException;
import com.prueba.crud.mappers.UserMapper;
import com.prueba.crud.repositories.IUserRepository;
import com.prueba.crud.user.UserRequest;
import com.prueba.crud.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    public UserResponse saveUser(UserRequest request) {
        UserModel user = UserModel.builder()
                .name(request.name())
                .build();
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    public UserResponse updateUserById(UserRequest request, Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        user.setName(request.name());
        return userMapper.toResponse(userRepository.save(user));
    }

    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}