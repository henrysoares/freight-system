package com.hencarvalho.freightsystem.domain;

import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/** Entidade que representa os dados do veiculo. */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "VEHICLE_DETAILS")
public class VehicleDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "VEHICLE_ID")
  Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CUSTOMER_ID")
  Customer customer;

  @Column(name = "VEHICLE_MODEL", nullable = false, length = 30)
  String model;

  @Column(name = "VEHICLE_DOCUMENT", nullable = false, unique = true, length = 30)
  String carDocument;

  @Column(name = "DRIVER_DOCUMENT", nullable = false, unique = true, length = 30)
  String driverDocument;

  @Column(name = "DRIVER_ANNOTATIONS")
  String driverAnnotations;

  @Column(name = "DAT_CREATION")
  ZonedDateTime creationDate;

  @Column(name = "DAT_UPDATE")
  ZonedDateTime updateDate;

  @PrePersist
  void setInitialDates() {
    this.creationDate = ZonedDateTime.now();
    this.updateDate = ZonedDateTime.now();
  }

  @PreUpdate
  void setUpdateDate() {
    this.updateDate = ZonedDateTime.now();
  }
}
