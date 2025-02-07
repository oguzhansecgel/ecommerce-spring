package com.shop.customer_service.dto.request.address;


public class CreateAddressRequest {

    private String street;          // Sokak Adı
    private String buildingNumber;  // Bina No
    private String apartmentNumber; // Daire No
    private String district;        // Mahalle/İlçe
    private String city;            // Şehir
    private String state;           // Eyalet/İl
    private String country;         // Ülke
    private String zipcode;         // Posta Kodu
    private String addressLabel;    // Adres Etiketi (Ev, İş vs.)

    private int userId;

    public CreateAddressRequest() {
    }

    public CreateAddressRequest(String street, String buildingNumber, String apartmentNumber, String district, String city, String state, String country, String zipcode, String addressLabel, int userId) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.addressLabel = addressLabel;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
