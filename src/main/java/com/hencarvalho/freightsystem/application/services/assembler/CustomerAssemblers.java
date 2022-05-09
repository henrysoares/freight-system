package com.hencarvalho.freightsystem.application.services.assembler;

import com.hencarvalho.freightsystem.domain.Address;
import com.hencarvalho.freightsystem.domain.Customer;
import com.hencarvalho.freightsystem.interfaces.requester.AddressDTO;
import com.hencarvalho.freightsystem.interfaces.requester.CustomerDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssemblers {

  public CustomerDTO assembleDTO(@NonNull final Customer customer) {
    return CustomerDTO.builder()
        .name(customer.getName())
        .phone(customer.getPhone())
        .email(customer.getEmail())
        .document(customer.getDocument())
        .score(customer.getScore())
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
}
