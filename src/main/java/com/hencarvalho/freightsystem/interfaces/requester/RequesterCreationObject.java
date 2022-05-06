package com.hencarvalho.freightsystem.interfaces.requester;

import com.hencarvalho.freightsystem.domain.Requester;
import com.hencarvalho.freightsystem.util.UserScore;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/** Pojo responsavel por conter as informações necessarias para a criação de um requester. */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RequesterCreationObject implements Serializable {

  private static final long serialVersionUID = 6858521039066333936L;

  @NotNull
  @Max(60)
  String name;

  @NotNull
  @Size(min = 12, max = 12)
  String document;

  @Valid AddressCreationRequest address;

  public Requester toEntity() {
    return Requester.builder()
        .name(name)
        .document(document)
        .creationDate(ZonedDateTime.now())
        .address(address.toEntity())
        .score(UserScore.NONE)
        .userCode(UUID.randomUUID())
        .updateDate(ZonedDateTime.now())
        .build();
  }
}
