package com.shop.customer_service.service;

import com.shop.customer_service.config.JwtService;
import com.shop.customer_service.dto.request.AuthenticationRequest;
import com.shop.customer_service.dto.request.RegisterRequest;
import com.shop.customer_service.dto.response.LoginResponse;
import com.shop.customer_service.dto.response.RegisterResponse;
import com.shop.customer_service.entity.Role;
import com.shop.customer_service.entity.User;
import com.shop.customer_service.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager  authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(RegisterRequest request) {
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        User savedUser =  userRepository.save(user);
        return new RegisterResponse(savedUser.getFirstname(),savedUser.getLastname(), savedUser.getEmail(), savedUser.getPassword());

    }

    public LoginResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        String jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken, user.getRole().toString(),user.getId());

    }
   /* public Optional<GetByIdCustomerResponse> getByIdCustomerId(int id)
    {
        Optional<User> user = userRepository.findById(id);
        return user.map(CustomerMapping.INSTANCE::getByIdCustomer);
    }*/
}