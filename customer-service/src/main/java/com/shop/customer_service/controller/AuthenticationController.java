package com.shop.customer_service.controller;
import com.shop.customer_service.dto.request.user.AuthenticationRequest;
import com.shop.customer_service.dto.request.user.RegisterRequest;
import com.shop.customer_service.dto.response.user.LoginResponse;
import com.shop.customer_service.dto.response.user.RegisterResponse;
import com.shop.customer_service.dto.response.user.UserInfoResponse;
import com.shop.customer_service.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

  /*  @GetMapping("/get/by/{id}")
    public Optional<GetByIdCustomerResponse> getById(@PathVariable int id)
    {
        return authenticationService.getByIdCustomerId(id);
    }*/
    @GetMapping("/user/info/{customerId}")
    public UserInfoResponse customerInfo(@PathVariable("customerId") Integer customerId)
    {
        return authenticationService.userInfo(customerId);
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorization) {
        String token = authorization.startsWith("Bearer ") ? authorization.substring(7) : authorization;

        if (authenticationService.isTokenBlacklisted(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }

        authenticationService.logout(token);
        return ResponseEntity.ok("Çıkış yapıldı.");
    }

}