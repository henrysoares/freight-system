package com.hencarvalho.freightsystem.application.assembler;

import com.hencarvalho.freightsystem.domain.Address;
import com.hencarvalho.freightsystem.domain.Customer;
import com.hencarvalho.freightsystem.domain.VehicleDetails;
import com.hencarvalho.freightsystem.interfaces.requester.dto.AddressDTO;
import com.hencarvalho.freightsystem.interfaces.requester.dto.CustomerDTO;
import com.hencarvalho.freightsystem.interfaces.requester.dto.VehicleDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssemblers {

  public CustomerDTO assembleDTO(@NonNull final Customer customer, VehicleDTO vehicleDTO) {
    return CustomerDTO.builder()
        .name(customer.getName())
        .phone(customer.getPhone())
        .email(customer.getEmail())
        .document(customer.getDocument())
        .score(customer.getScore())
        .vehicleDTO(vehicleDTO)
        .addressDTO(assembleAddressDTO(customer.getAddress()))
        .build();
  }

  public AddressDTO assembleAddressDTO(@NonNull final Address address) {
    return AddressDTO.builder()
        .street(address.getStreet())
        .block(address.getBlock())
        .cep(address.getCep())
        .city(address.getCity())
        .number(address.getNumber())
        .build();
  }

  public VehicleDTO assembleVehicleDTO(@NonNull final VehicleDetails vehicleDetails) {
    return VehicleDTO.builder()
        .model(vehicleDetails.getModel())
        .vehicleDocument(vehicleDetails.getCarDocument())
        .driverDocument(vehicleDetails.getDriverDocument())
        .build();
  }
}
