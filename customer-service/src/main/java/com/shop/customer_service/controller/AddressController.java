package com.shop.customer_service.controller;

import com.shop.customer_service.config.JwtService;
import com.shop.customer_service.dto.request.address.CreateAddressRequest;
import com.shop.customer_service.dto.request.address.UpdateAddressRequest;
import com.shop.customer_service.dto.response.address.*;
import com.shop.customer_service.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;
    private final JwtService jwtService;

    public AddressController(AddressService addressService, JwtService jwtService) {
        this.addressService = addressService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create/address")
    public CreateAddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest,
                                               @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        int userId = jwtService.extractUserIdFromToken(token);
        createAddressRequest.setUserId(userId);
        System.out.println("UserId geldi: " + userId);
        return addressService.createAddress(createAddressRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdAddressResponse> getAddressById(@PathVariable int id) {
        Optional<GetByIdAddressResponse> response = addressService.getByIdAddress(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<GetAllAddressResponse> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/get/by/address/with/customer/{userId}")
    public List<GetAddressByCustomer> getAddressByCustomers(@PathVariable Integer userId)
    {
        return addressService.getAddressByCustomers(userId);
    }

    @PutMapping("/update/{id}")
    public UpdateAddressResponse updateAddress(@PathVariable int id,
                                               @RequestBody UpdateAddressRequest updateAddressRequest,
                                               @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        int userId = jwtService.extractUserIdFromToken(token);
        updateAddressRequest.setUserId(userId);
        return addressService.updateAddress(id, updateAddressRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
