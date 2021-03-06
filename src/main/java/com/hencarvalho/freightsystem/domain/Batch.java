package com.hencarvalho.freightsystem.domain;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.hencarvalho.freightsystem.infrastructure.BatchStatus;
import com.hencarvalho.freightsystem.infrastructure.BatchType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/** Entidade que representa o lote (carga). */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "BATCH")
public class Batch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BATCH_ID")
  Long id;

  @Column(name = "DRIVER_CUSTOMER_CODE")
  UUID driverCode;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CUSTOMER_REQUESTER_ID")
  Customer customerRequester;

  @Column(name = "BATCH_CODE", nullable = false, length = 50, unique = true)
  String code;

  @Column(name = "BATCH_UNLOCK_CODE", nullable = false, length = 10)
  String unlockCode;

  @Column(name = "BATCH_ESTIMATED_TIME", nullable = false)
  int estimatedTime;

  @Column(name = "BATCH_DESCRIPTION")
  String description;

  @Column(name = "BATCH_WEIGHT", nullable = false)
  float weight;

  @Column(name = "BATCH_PRICE", nullable = false)
  float price;

  @Column(name = "BATCH_TYPE")
  @Enumerated(EnumType.STRING)
  BatchType batchType;

  @Setter
  @Column(name = "BATCH_STATUS")
  @Enumerated(EnumType.STRING)
  BatchStatus batchStatus;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "BATCH_DESTINY_ADDRESS", nullable = false)
  Address address;

  @Column(name = "DAT_CREATION", nullable = false)
  ZonedDateTime creationDate;

  @PrePersist
  void setInitialData(){
    this.creationDate = ZonedDateTime.now();
    this.unlockCode = "fuck";
  }
}
