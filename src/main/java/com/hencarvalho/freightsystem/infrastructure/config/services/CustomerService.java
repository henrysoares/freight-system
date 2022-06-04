package com.hencarvalho.freightsystem.infrastructure.config.services;

import com.hencarvalho.freightsystem.domain.Customer;
import com.hencarvalho.freightsystem.interfaces.customer.VehicleDetailsCreationRequest;
import com.hencarvalho.freightsystem.interfaces.customer.dto.CustomerDTO;
import java.util.UUID;
import lombok.NonNull;

public interface CustomerService {

  void saveCustomerRequester(Customer customer);

  void saveCustomerDriver(Customer customer, VehicleDetailsCreationRequest vehicleDetails);

  CustomerDTO getLoggedUserInformation(@NonNull final String token);

  Customer getCustomer(@NonNull final UUID customerCode);

  Customer getCustomerByEmail(@NonNull final String email);

  void deleteByEmail(@NonNull final String email);
}
