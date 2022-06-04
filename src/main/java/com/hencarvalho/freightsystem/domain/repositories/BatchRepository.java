package com.hencarvalho.freightsystem.domain.repositories;

import com.hencarvalho.freightsystem.domain.Batch;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    Optional<Batch> findByCode(@NonNull final String batchCode);
}
