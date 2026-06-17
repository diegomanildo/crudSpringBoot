package com.prueba.crud.servicies;

import com.prueba.crud.auth.AuthResponse;
import com.prueba.crud.auth.LoginRequest;
import com.prueba.crud.auth.RegisterRequest;
import com.prueba.crud.exceptions.ResourceNotFoundException;
import com.prueba.crud.jwt.JwtService;
import com.prueba.crud.repositories.IUserRepository;
import com.prueba.crud.user.Role;
import com.prueba.crud.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
        UserModel user = userRepository.findByName(loginRequest.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found: " + loginRequest.getName()));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        UserModel user = UserModel.builder()
                .name(registerRequest.getName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
