package com.hencarvalho.freightsystem.interfaces.requester;

import com.hencarvalho.freightsystem.domain.repositories.RequesterRepository;
import com.hencarvalho.freightsystem.domain.services.UserServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/user/requester")
public class RequesterResource {

  @Autowired UserServiceImpl userService;

  @Autowired RequesterRepository requesterRepository;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createUserRequester(
      @RequestBody @NonNull RequesterCreationObject request) {
    userService.createRequester(request);

    return ResponseEntity.ok().build();
  }
}
