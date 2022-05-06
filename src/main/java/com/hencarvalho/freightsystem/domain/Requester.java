package com.hencarvalho.freightsystem.domain;

import com.hencarvalho.freightsystem.util.UserScore;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/** Entidade que representa o requisitante de um pedido. */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "REQUESTER")
public class Requester {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "REQUESTER_ID")
  Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ADDRESS_ID")
  Address address;

  @Column(name = "REQUESTER_NAME", nullable = false, unique = true, length = 60)
  String name;

  @Column(name = "REQUESTER_DOCUMENT", nullable = false, unique = true, length = 20)
  String document;

  @Column(name = "REQUESTER_USER_CODE", nullable = false, unique = true, length = 36)
  UUID userCode;

  @Column(name = "REQUESTER_SCORE", nullable = false)
  @Enumerated(EnumType.STRING)
  UserScore score;

  @Column(name = "DAT_CREATION")
  ZonedDateTime creationDate;

  @Column(name = "DAT_UPDATE")
  ZonedDateTime updateDate;
}
