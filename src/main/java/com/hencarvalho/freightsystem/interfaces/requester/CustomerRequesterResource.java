package com.hencarvalho.freightsystem.interfaces.requester;

import com.hencarvalho.freightsystem.application.services.CustomerService;
import com.hencarvalho.freightsystem.infrastructure.util.CustomerType;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/user/requester")
public class CustomerRequesterResource {

  CustomerService customerService;

  /**
   * Metodo para criação de ususarios do tipo REQUESTER.
   *
   * @param request
   * @return Status 201 caso o usuario seja criado com sucesso.
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createCustomerRequester(
      @Valid @RequestBody @NonNull CustomerCreationRequest request) {
    customerService.saveCustomer(request.toEntity(CustomerType.REQUESTER));

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAuthorizedUserInfo(
      @RequestHeader("AUTHORIZATION") String accessToken) {
    return ResponseEntity.ok(customerService.getLoggedUserInformation(accessToken));
  }
}
