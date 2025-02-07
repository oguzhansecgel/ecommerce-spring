package com.shop.customer_service.dto.response.user;

public class LoginResponse {
    private String token;
    private String role;
    private int userId;

    public LoginResponse() {
    }

    public LoginResponse(String token, String role, int userId) {
        this.token = token;
        this.role = role;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
