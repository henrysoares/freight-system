package com.hencarvalho.freightsystem.domain.services;

import com.hencarvalho.freightsystem.domain.repositories.RequesterRepository;
import com.hencarvalho.freightsystem.interfaces.requester.RequesterCreationObject;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class UserServiceImpl {

  RequesterRepository requesterRepository;

  public void createRequester(@NonNull RequesterCreationObject requesterCreationObject) {
    requesterRepository.save(requesterCreationObject.toEntity());
  }

  public void createDriver() {}
}
