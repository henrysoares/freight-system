package com.hencarvalho.freightsystem.application.services;

import com.auth0.jwt.JWTVerifier;
import com.hencarvalho.freightsystem.application.services.exceptions.CustomerCreationException;
import com.hencarvalho.freightsystem.application.services.exceptions.CustomerNotFoundException;
import com.hencarvalho.freightsystem.domain.Customer;
import com.hencarvalho.freightsystem.domain.repositories.CustomerRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

  CustomerRepository customerRepository;

  PasswordEncoder passwordEncoder;

  JWTVerifier jwtVerifier;

  /**
   * Metodo responsavel por fazer a persistencia do ususario na base.
   *
   * @param user
   */
  @Override
  public void saveCustomer(Customer user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      customerRepository.save(user);

      log.info(String.format("%s successfully saved.", user.getEmail()));
    } catch (Exception ex) {
      log.error(String.format("Was not possible to save user %s", user.getEmail()), ex);

      throw new CustomerCreationException(ex.getMessage());
    }
  }

  @Override
  public Customer getLoggedUserInformation(@NonNull String token) {
    final var details = jwtVerifier.verify(token.substring("Bearer ".length()));
    var email = details.getSubject();

    final var customer = customerRepository.findByEmail(email);

    return getCustomer(customer);
  }

  @Override
  public Customer getCustomer(@NonNull UUID customerCode) {
    final var customer = customerRepository.findByCustomerCode(customerCode);
    return getCustomer(customer);
  }

  @Override
  public Customer getCustomerByEmail(@NonNull String email) {
    final var customer = customerRepository.findByEmail(email);
    return getCustomer(customer);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final var customer = getCustomerByEmail(email);

    // final var authority = new SimpleGrantedAuthority(customer.getType().name());

    return User.builder()
        .username(email)
        .password(customer.getPassword())
        .roles(customer.getType().name())
        .build();
  }

  private Customer getCustomer(final Optional<Customer> customer) {
    if (customer.isEmpty()) {
      throw new CustomerNotFoundException();
    }

    return customer.get();
  }
}
