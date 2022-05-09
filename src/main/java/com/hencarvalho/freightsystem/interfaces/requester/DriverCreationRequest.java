package com.hencarvalho.freightsystem.interfaces.requester;

import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class DriverCreationRequest extends CustomerCreationRequest {
  @Setter @Valid VehicleDetailsCreationRequest vehicleDetails;
}
