package com.hencarvalho.freightsystem.domain;

import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/** Entidade que representa o lote (carga). */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Entity
@Table(name = "BATCH")
public class Batch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BATCH_ID")
  Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "DRIVER_ID")
  Driver driver;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "OWNER_ID")
  Requester requester;

  @Column(name = "BATCH_CODE", nullable = false, length = 50, unique = true)
  String code;

  @Column(name = "BATCH_UNLOCK_CODE", nullable = false, length = 10)
  String unlockCode;

  @Column(name = "ESTIMATED_TIME", nullable = false)
  String estimatedTime;

  @Column(name = "DAT_CREATION", nullable = false)
  ZonedDateTime creationDate;
}
