package com.hencarvalho.freightsystem.domain;

import com.hencarvalho.freightsystem.infrastructure.util.CustomerScore;
import com.hencarvalho.freightsystem.infrastructure.util.CustomerType;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/** Entidade que representa os dados do ususario. */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CUSTOMER_ID")
  Long id;

  @Size(min = 10, max = 60)
  @Column(name = "CUSTOMER_NAME", nullable = false)
  String name;

  @Email
  @Column(name = "CUSTOMER_EMAIL", nullable = false, unique = true)
  String email;

  @Setter
  @Column(name = "CUSTOMER_PASSWORD")
  String password;

  @Column(name = "CUSTOMER_DOCUMENT", nullable = false, unique = true)
  String document;

  @Enumerated(EnumType.STRING)
  @Column(name = "CUSTOMER_TYPE", nullable = false)
  CustomerType type;

  @Column(name = "CUSTOMER_CODE", nullable = false, unique = true)
  UUID customerCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "CUSTOMER_SCORE", nullable = false)
  CustomerScore score;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ADDRESS_ID")
  Address address;

  @Column(name = "DAT_CREATION")
  ZonedDateTime creationDate;

  @Column(name = "DAT_UPDATE")
  ZonedDateTime updateDate;

  @PrePersist
  void setInitialData() {
    this.customerCode = UUID.randomUUID();
    this.creationDate = ZonedDateTime.now();
    this.updateDate = ZonedDateTime.now();
    this.score = CustomerScore.NONE;
  }

  @PreUpdate
  void setUpdateDate() {
    this.updateDate = ZonedDateTime.now();
  }
}
