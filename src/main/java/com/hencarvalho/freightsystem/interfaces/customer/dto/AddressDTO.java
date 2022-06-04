package com.hencarvalho.freightsystem.interfaces.customer.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class AddressDTO implements Serializable {

  private static final long serialVersionUID = -6709821175726499469L;

  String street;

  String block;

  int number;

  String city;

  String cep;
}
