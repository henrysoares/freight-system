package com.hencarvalho.freightsystem.domain;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/** Entidade que representa os dados de um endereço. */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Entity
@Table(name = "ADDRESS")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ADDRESS_ID")
  Long id;

  @Column(name = "ADDRESS_STREET", nullable = false, length = 60)
  String street;

  @Column(name = "ADDRESS_BLOCK", nullable = false, length = 60)
  String block;

  @Positive
  @Size(min = 1)
  @Column(name = "ADDRESS_NUMBER", nullable = false, length = 10)
  int number;

  @Column(name = "ADDRESS_CITY", nullable = false, length = 20)
  String city;

  @Column(name = "ADDRESS_CODE", nullable = false, length = 10)
  String CEP;

  @Column(name = "DAT_CREATION")
  ZonedDateTime creationDate;

  @Column(name = "DAT_UPDATE")
  ZonedDateTime updateDate;
}
