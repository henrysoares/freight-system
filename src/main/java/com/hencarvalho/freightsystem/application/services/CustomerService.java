package com.hencarvalho.freightsystem.application.services;

import com.hencarvalho.freightsystem.domain.Customer;
import java.util.UUID;
import lombok.NonNull;

public interface CustomerService {

  void saveCustomer(Customer customer);

  Customer getLoggedUserInformation(@NonNull final String token);

  Customer getCustomer(@NonNull final UUID customerCode);

  Customer getCustomerByEmail(@NonNull final String email);
}
