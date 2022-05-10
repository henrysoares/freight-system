package com.hencarvalho.freightsystem.interfaces.requester;

import com.hencarvalho.freightsystem.domain.Address;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressCreationRequest implements Serializable {

  private static final long serialVersionUID = -5970672938384405063L;

  @NotNull
  @Size(min = 5, max = 30)
  String street;

  @NotNull
  @Size(min = 5, max = 30)
  String block;

  @NotNull
  @Size(min = 3, max = 30)
  String city;

  @NotNull
  @Size(min = 8, max = 8)
  String cep;

  int number;

  public Address toEntity() {
    return Address.builder()
        .street(street)
        .block(block)
        .city(city)
        .cep(cep)
        .number(number)
        .creationDate(ZonedDateTime.now())
        .updateDate(ZonedDateTime.now())
        .build();
  }
}
