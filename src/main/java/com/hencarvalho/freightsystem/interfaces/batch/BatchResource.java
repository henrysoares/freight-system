package com.hencarvalho.freightsystem.interfaces.batch;

import com.hencarvalho.freightsystem.application.BatchService;
import com.hencarvalho.freightsystem.application.assemblers.BatchAssembler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/batch")
@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BatchResource {

    BatchService service;

    @PostMapping("/create")
    public ResponseEntity<BatchResponse> createBatch(@RequestBody final BatchCreationRequest request){
        final var batch = service.createBatch(request);

        return ResponseEntity.ok(batch);
    }

    @GetMapping("/confirm/{batchCode}")
    public ResponseEntity<Object> confirmBatch(@PathVariable("batchCode")UUID batchCode){
        service.confirm(batchCode);

        return ResponseEntity.ok().build();
    }

}
