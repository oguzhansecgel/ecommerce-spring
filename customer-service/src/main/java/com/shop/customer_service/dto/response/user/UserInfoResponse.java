package com.shop.customer_service.dto.response.user;

public class UserInfoResponse {
    private String name;
    private String surname;

    public UserInfoResponse() {
    }

    public UserInfoResponse(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
