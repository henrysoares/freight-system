package com.hencarvalho.freightsystem.interfaces.customer;

import com.hencarvalho.freightsystem.domain.Customer;
import com.hencarvalho.freightsystem.domain.VehicleDetails;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VehicleDetailsCreationRequest implements Serializable {

  private static final long serialVersionUID = 6096433677115694629L;

  String model;

  String carDocument;

  String driverDocument;

  public VehicleDetails toEntity(@NonNull final Customer customer) {
    return VehicleDetails.builder()
        .customer(customer)
        .carDocument(carDocument)
        .driverDocument(driverDocument)
        .model(model)
        .build();
  }
}
