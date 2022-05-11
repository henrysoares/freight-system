package com.hencarvalho.freightsystem.interfaces.batch;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/batch")
public class BatchResource {

}
