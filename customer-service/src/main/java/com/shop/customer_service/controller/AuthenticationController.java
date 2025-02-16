package com.shop.customer_service.controller;
import com.shop.customer_service.dto.request.user.AuthenticationRequest;
import com.shop.customer_service.dto.request.user.RegisterRequest;
import com.shop.customer_service.dto.request.user.TokenRefreshRequest;
import com.shop.customer_service.dto.response.user.LoginResponse;
import com.shop.customer_service.dto.response.user.RegisterResponse;
import com.shop.customer_service.dto.response.user.TokenResponse;
import com.shop.customer_service.dto.response.user.UserInfoResponse;
import com.shop.customer_service.service.AuthenticationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.http.auth.InvalidCredentialsException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody TokenRefreshRequest refreshRequest) {
        try {
            TokenResponse response = authenticationService.refreshToken(refreshRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);  // Token invalid ise
        }
    }
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized | Invalid username or password",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request | Missing or invalid request parameters",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            LoginResponse response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorMessage("Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Bad Request"));
        }
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