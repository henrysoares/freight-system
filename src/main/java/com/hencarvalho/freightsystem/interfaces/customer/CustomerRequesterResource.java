package com.hencarvalho.freightsystem.interfaces.customer;

import com.hencarvalho.freightsystem.infrastructure.config.services.CustomerService;
import com.hencarvalho.freightsystem.infrastructure.util.CustomerType;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/user")
public class CustomerRequesterResource {

  CustomerService customerService;

  /**
   * Metodo para criação de ususarios do tipo REQUESTER.
   *
   * @param request
   * @return Status 201 caso o usuario seja criado com sucesso.
   */
  @PostMapping(path = "/requester", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createCustomerRequester(
      @Valid @RequestBody @NonNull CustomerCreationRequest request) {
    customerService.saveCustomerRequester(request.toEntity(CustomerType.REQUESTER));

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Metodo para criação de ususarios do tipo DRIVER.
   *
   * @param request
   * @return Status 201 caso o usuario seja criado com sucesso.
   */
  @PostMapping(path = "/driver", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createCustomerDriver(
      @Valid @RequestBody @NonNull DriverCreationRequest request) {
    final var vehicleDetails = request.getVehicleDetails();

    customerService.saveCustomerDriver(request.toEntity(CustomerType.DRIVER), vehicleDetails);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAuthorizedUserInfo(
      @RequestHeader("AUTHORIZATION") String accessToken) {
    return ResponseEntity.ok(customerService.getLoggedUserInformation(accessToken));
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteLoggedUser(
      @RequestHeader("AUTHORIZATION") String accessToken) {
    customerService.deleteByEmail(accessToken);
    return ResponseEntity.ok().build();
  }

  @PatchMapping
  public ResponseEntity<Object> updateLoggedUser(
      @RequestHeader("AUTHORIZATION") String accessToken) {
    customerService.deleteByEmail(accessToken);
    return ResponseEntity.ok().build();
  }
}
