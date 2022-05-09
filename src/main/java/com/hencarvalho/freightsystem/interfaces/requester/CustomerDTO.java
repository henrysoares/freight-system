package com.hencarvalho.freightsystem.interfaces.requester;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hencarvalho.freightsystem.infrastructure.util.CustomerScore;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class CustomerDTO implements Serializable {

  private static final long serialVersionUID = 1279390064725387097L;

  String name;

  String phone;

  String email;

  String document;

  CustomerScore score;

  @JsonProperty("address")
  AddressDTO addressDTO;
}
