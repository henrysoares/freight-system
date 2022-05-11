package com.hencarvalho.freightsystem.application.services;

import com.auth0.jwt.JWTVerifier;
import com.hencarvalho.freightsystem.application.assembler.CustomerAssemblers;
import com.hencarvalho.freightsystem.application.exceptions.CustomerCreationException;
import com.hencarvalho.freightsystem.application.exceptions.CustomerNotFoundException;
import com.hencarvalho.freightsystem.application.services.CustomerService;
import com.hencarvalho.freightsystem.domain.Customer;
import com.hencarvalho.freightsystem.domain.VehicleDetails;
import com.hencarvalho.freightsystem.domain.repositories.CustomerRepository;
import com.hencarvalho.freightsystem.domain.repositories.VehicleDetailsRepository;
import com.hencarvalho.freightsystem.interfaces.requester.VehicleDetailsCreationRequest;
import com.hencarvalho.freightsystem.interfaces.requester.dto.CustomerDTO;
import com.hencarvalho.freightsystem.interfaces.requester.dto.VehicleDTO;
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

  VehicleDetailsRepository vehicleDetailsRepository;

  PasswordEncoder passwordEncoder;

  JWTVerifier jwtVerifier;

  CustomerAssemblers customerAssemblers;

  /**
   * Metodo responsavel por fazer a persistencia do ususario na base.
   *
   * @param user
   */
  @Override
  public void saveCustomerRequester(Customer user) {
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
  public void saveCustomerDriver(Customer user, VehicleDetailsCreationRequest vehicleDetails) {
    try {

      user.setPassword(passwordEncoder.encode(user.getPassword()));
      final var customer = customerRepository.save(user);

      vehicleDetailsRepository.save(vehicleDetails.toEntity(customer));

      log.info(String.format("%s successfully saved.", user.getEmail()));
    } catch (Exception ex) {
      log.error(String.format("Was not possible to save user %s", user.getEmail()), ex);

      throw new CustomerCreationException(ex.getMessage());
    }
  }

  /**
   * Metodo responsavel por retornar os dados do ususario logado.
   *
   * @param token Token que contem o email.
   * @return DTO dos dados do usuario.
   */
  @Override
  public CustomerDTO getLoggedUserInformation(@NonNull String token) {
    final var details = jwtVerifier.verify(token.substring("Bearer ".length()));
    var email = details.getSubject();

    final var vehicleDetails = getVehicleDetails(email);

    return customerAssemblers.assembleDTO(
        customerRepository.findByEmail(email).get(), vehicleDetails);
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
  public void deleteByEmail(@NonNull String token) {
    final var details = jwtVerifier.verify(token.substring("Bearer ".length()));
    var email = details.getSubject();

    final var customer = getCustomerByEmail(email);
    customerRepository.delete(customer);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final var customer = getCustomerByEmail(email);

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

  private VehicleDTO getVehicleDetails(@NonNull final String email) {
    final var vehicleDetails = vehicleDetailsRepository.findVehicleByCustomerEmail(email);

    if (vehicleDetails.isEmpty()) {
      return customerAssemblers.assembleVehicleDTO(new VehicleDetails());
    }

    return customerAssemblers.assembleVehicleDTO(vehicleDetails.get());
  }
}
