package com.hencarvalho.freightsystem.domain.repositories;

import com.hencarvalho.freightsystem.domain.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequesterRepository extends JpaRepository<Requester, Long> {}
