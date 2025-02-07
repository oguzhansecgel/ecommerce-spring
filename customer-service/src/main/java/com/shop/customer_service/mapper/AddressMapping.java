package com.shop.customer_service.mapper;

import com.shop.customer_service.dto.request.address.CreateAddressRequest;
import com.shop.customer_service.dto.request.address.UpdateAddressRequest;
import com.shop.customer_service.dto.response.address.GetAddressByCustomer;
import com.shop.customer_service.dto.response.address.GetAllAddressResponse;
import com.shop.customer_service.dto.response.address.GetByIdAddressResponse;
import com.shop.customer_service.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapping {


    AddressMapping INSTANCE = Mappers.getMapper(AddressMapping.class);

    @Mapping(target = "user.id",source = "userId")
    Address createAddress(CreateAddressRequest request);

    @Mapping(target = "user.id",source = "userId")
    Address updateAddress(UpdateAddressRequest request,@MappingTarget Address address);

    @Mapping(target = "userId",source = "user.id")
    GetByIdAddressResponse getByIdAddress(Address address);

    @Mapping(target = "userId",source = "user.id")
    GetAllAddressResponse getAllAddress(Address address);
    List<GetAllAddressResponse> getAllAddressToList(List<Address> address);

    @Mapping(target = "userId",source = "user.id")
    GetAddressByCustomer getByAddressWithCustomer(Address address);
    List<GetAddressByCustomer> getAllAddressByCustomerToList(List<Address> address);
}
