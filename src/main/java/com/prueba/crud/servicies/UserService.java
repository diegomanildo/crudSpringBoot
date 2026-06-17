package com.prueba.crud.servicies;

import com.prueba.crud.entities.UserModel;
import com.prueba.crud.exceptions.ResourceNotFoundException;
import com.prueba.crud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel updateUserById(UserModel request, Long id) {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));

        user.setName(request.getName());

        return userRepository.save(user);
    }

    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        }  catch (Exception e) {
            return false;
        }
    }
}
