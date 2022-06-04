package com.hencarvalho.freightsystem.domain.repositories;

import com.hencarvalho.freightsystem.domain.Customer;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findByCustomerCode(@NonNull final String customerCode);

  Optional<Customer> findByEmail(@NonNull final String email);
}
