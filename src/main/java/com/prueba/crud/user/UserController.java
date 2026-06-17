package com.prueba.crud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(UserModel user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@PathVariable Long id, @RequestBody UserModel user) {
        return userService.updateUserById(user, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUserById(@PathVariable Long id) {
        boolean ok = userService.deleteUserById(id);
        if (ok) {
            return "User with id " + id + " deleted";
        } else {
            return "ERROR: User with id " + id + " could not be deleted";
        }
    }
}
