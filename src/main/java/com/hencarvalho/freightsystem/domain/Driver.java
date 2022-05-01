package com.hencarvalho.freightsystem.domain;

import com.hencarvalho.freightsystem.util.UserScore;
import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/** Entidade que representa os dados do motorista. */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Entity
@Table(name = "DRIVER")
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DRIVER_ID")
  Long id;

  @Column(name = "DRIVER_NAME", nullable = false, length = 30)
  String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ADDRESS_ID")
  Address address;

  @Column(name = "CAR_MODEL", nullable = false, length = 30)
  String model;

  @Column(name = "CAR_DOCUMENT", nullable = false, unique = true, length = 30)
  String carDocument;

  @Column(name = "DRIVER_DOCUMENT", nullable = false, unique = true, length = 30)
  String driverDocument;

  @Column(name = "DRIVER_SCORE", nullable = false, length = 20)
  UserScore score;

  @Column(name = "DAT_CREATION")
  ZonedDateTime creationDate;

  @Column(name = "DAT_UPDATE")
  ZonedDateTime updateDate;
}
