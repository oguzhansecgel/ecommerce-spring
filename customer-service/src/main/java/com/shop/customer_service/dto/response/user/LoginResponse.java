package com.shop.customer_service.dto.response.user;

public class LoginResponse {
    private String token;
    private String role;
    private String name;
    private String surname;
    private int userId;

    public LoginResponse() {
    }

    public LoginResponse(String token, String role, String name, String surname, int userId) {
        this.token = token;
        this.role = role;
        this.name = name;
        this.surname = surname;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
