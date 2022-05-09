package com.hencarvalho.freightsystem.interfaces.requester;

import com.hencarvalho.freightsystem.domain.Customer;
import com.hencarvalho.freightsystem.infrastructure.util.CustomerType;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** Pojo responsavel por conter as informações necessarias para a criação de um requester. */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerCreationRequest implements Serializable {

  private static final long serialVersionUID = 6858521039066333936L;

  @NotNull
  @Max(60)
  String name;

  @Email @NotNull String email;

  @NotNull String password;

  @NotNull
  @Size(min = 12, max = 12)
  String document;

  @Valid AddressCreationRequest address;

  public Customer toEntity(@NonNull final CustomerType type) {
    return Customer.builder()
        .name(name)
        .document(document)
        .type(type)
        .email(email)
        .password(password)
        .address(address.toEntity())
        .build();
  }
}
