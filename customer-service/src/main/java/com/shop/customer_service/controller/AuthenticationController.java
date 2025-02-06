package com.shop.customer_service.controller;
import com.shop.customer_service.dto.request.AuthenticationRequest;
import com.shop.customer_service.dto.request.RegisterRequest;
import com.shop.customer_service.dto.response.LoginResponse;
import com.shop.customer_service.dto.response.RegisterResponse;
import com.shop.customer_service.service.AuthenticationService;
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

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> register(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
}