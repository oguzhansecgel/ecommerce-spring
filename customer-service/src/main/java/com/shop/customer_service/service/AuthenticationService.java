package com.shop.customer_service.service;

import com.shop.customer_service.config.JwtService;
import com.shop.customer_service.dto.request.user.AuthenticationRequest;
import com.shop.customer_service.dto.request.user.RegisterRequest;
import com.shop.customer_service.dto.request.user.TokenRefreshRequest;
import com.shop.customer_service.dto.response.user.LoginResponse;
import com.shop.customer_service.dto.response.user.RegisterResponse;
import com.shop.customer_service.dto.response.user.TokenResponse;
import com.shop.customer_service.dto.response.user.UserInfoResponse;
import com.shop.customer_service.entity.BlacklistToken;
import com.shop.customer_service.entity.Role;
import com.shop.customer_service.entity.User;
import com.shop.customer_service.repository.BlacklistRepository;
import com.shop.customer_service.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager  authenticationManager;
    private final BlacklistRepository blacklistRepository;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, BlacklistRepository blacklistRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.blacklistRepository = blacklistRepository;
    }

    public RegisterResponse register(RegisterRequest request) {
        Optional<User> email = userRepository.findByEmail(request.getEmail());
        if (email.isPresent())
        {
            throw new RuntimeException("Email already in use");
        }
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        User savedUser =  userRepository.save(user);
        return new RegisterResponse(savedUser.getFirstname(),savedUser.getLastname(), savedUser.getEmail(), savedUser.getPassword());

    }

    public LoginResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        String jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken, user.getRole().toString(),user.getFirstname(), user.getLastname(), user.getId());

    }
    public TokenResponse refreshToken(TokenRefreshRequest refreshRequest) {
        String refreshToken = refreshRequest.getRefreshToken();
        if (jwtService.isRefreshTokenValid(refreshToken)) {
            String username = jwtService.extractUsername(refreshToken);
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String newAccessToken = jwtService.generateToken(user);

            String newRefreshToken = jwtService.generateRefreshToken(user);

            return new TokenResponse(newAccessToken, newRefreshToken);
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }
    public UserInfoResponse userInfo(int customerId)
    {
        User user = userRepository.findById(customerId).orElseThrow();
        return new UserInfoResponse(user.getFirstname(), user.getLastname());
    }
    public void logout(String token) {
        if (token != null && !token.isEmpty()) {
            BlacklistToken blacklistToken = new BlacklistToken();
            blacklistToken.setToken(token);
            blacklistRepository.save(blacklistToken);
        }
    }
    public boolean isTokenBlacklisted(String token) {
        return blacklistRepository.findByToken(token).isPresent();
    }

   /* public Optional<GetByIdCustomerResponse> getByIdCustomerId(int id)
    {
        Optional<User> user = userRepository.findById(id);
        return user.map(CustomerMapping.INSTANCE::getByIdCustomer);
    }*/


}