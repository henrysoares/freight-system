package com.hencarvalho.freightsystem.domain.repositories;

import com.hencarvalho.freightsystem.domain.VehicleDetails;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, Long> {

  @Query(
      value =
          "select * from vehicle_details vd "
              + "inner join customer c on vd.customer_id = c.customer_id "
              + "where c.customer_email = ?1",
      nativeQuery = true)
  Optional<VehicleDetails> findVehicleByCustomerEmail(@NonNull String email);
}
