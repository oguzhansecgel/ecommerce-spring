package com.shop.customer_service.service;

import com.shop.customer_service.dto.request.address.CreateAddressRequest;
import com.shop.customer_service.dto.request.address.UpdateAddressRequest;
import com.shop.customer_service.dto.response.address.*;
import com.shop.customer_service.entity.Address;
import com.shop.customer_service.mapper.AddressMapping;
import com.shop.customer_service.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {


    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public Optional<GetByIdAddressResponse> getByIdAddress(int id) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        return existingAddress.map(AddressMapping.INSTANCE::getByIdAddress);
    }
    public List<GetAllAddressResponse> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return AddressMapping.INSTANCE.getAllAddressToList(addresses);
    }
    public List<GetAddressByCustomer> getAddressByCustomers(int customerId)
    {
        List<Address> addresses = addressRepository.findAddressByUserId(customerId);
        return AddressMapping.INSTANCE.getAllAddressByCustomerToList(addresses);
    }
    public CreateAddressResponse createAddress(CreateAddressRequest request) {
        Address address = AddressMapping.INSTANCE.createAddress(request);
        Address savedAddress = addressRepository.save(address);
        return new CreateAddressResponse(
                savedAddress.getId(),
                savedAddress.getStreet(),
                savedAddress.getBuildingNumber(),
                savedAddress.getApartmentNumber(),
                savedAddress.getDistrict(),
                savedAddress.getCity(),
                savedAddress.getState(),
                savedAddress.getCountry(),
                savedAddress.getZipcode(),
                savedAddress.getAddressLabel(),
                savedAddress.getUser().getId()
        );
    }

    public UpdateAddressResponse updateAddress(int id, UpdateAddressRequest request) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new RuntimeException("Address not found");
        }
        Address address = optionalAddress.get();
        // Update işlemi için request'i mevcut entity üzerinde map ediyoruz
        Address updatedAddress = AddressMapping.INSTANCE.updateAddress(request, address);
        Address savedAddress = addressRepository.save(updatedAddress);
        return new UpdateAddressResponse(
                savedAddress.getId(),
                savedAddress.getStreet(),
                savedAddress.getBuildingNumber(),
                savedAddress.getApartmentNumber(),
                savedAddress.getDistrict(),
                savedAddress.getCity(),
                savedAddress.getState(),
                savedAddress.getCountry(),
                savedAddress.getZipcode(),
                savedAddress.getAddressLabel(),
                savedAddress.getUser().getId()
        );
    }

    public void deleteAddress(int id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found");
        }
        addressRepository.deleteById(id);
    }


}
