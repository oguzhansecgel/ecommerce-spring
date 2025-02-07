package com.shop.customer_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;          // Sokak Adı
    private String buildingNumber;  // Bina No
    private String apartmentNumber; // Daire No
    private String district;        // Mahalle/İlçe
    private String city;            // Şehir
    private String state;           // Eyalet/İl
    private String country;         // Ülke
    private String zipcode;         // Posta Kodu
    private String addressLabel;    // Adres Etiketi (Ev, İş vs.)

    // Many addresses can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // foreign key to user
    private User user;

    public Address() {
    }

    public Address(int id, String street, String buildingNumber, String apartmentNumber, String district, String city, String state, String country, String zipcode, String addressLabel, User user) {
        this.id = id;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.addressLabel = addressLabel;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(String addressLabel) {
        this.addressLabel = addressLabel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
