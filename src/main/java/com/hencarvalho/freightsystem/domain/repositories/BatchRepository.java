package com.hencarvalho.freightsystem.domain.repositories;

import com.hencarvalho.freightsystem.domain.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {
}
