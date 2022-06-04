package com.hencarvalho.freightsystem.application.assemblers;

import com.hencarvalho.freightsystem.domain.Batch;
import com.hencarvalho.freightsystem.infrastructure.BatchStatus;
import com.hencarvalho.freightsystem.interfaces.batch.BatchCreationRequest;
import com.hencarvalho.freightsystem.interfaces.batch.BatchResponse;
import lombok.NonNull;
import com.hencarvalho.freightsystem.domain.Customer;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class BatchAssembler {
    public Batch assemble(@NonNull final BatchCreationRequest request, @NonNull final Customer customer){
        return Batch.builder()
                .batchType(request.getBatchType())
                .address(request.getDestiny())
                .code(UUID.randomUUID().toString())
                .creationDate(ZonedDateTime.now())
                .customerRequester(customer)
                .description(request.getDescription())
                .price(30.4f)
                .weight(request.getBatchWeight())
                .unlockCode("AWED123")
                .batchStatus(BatchStatus.PENDING)
                .build();
    }

    public BatchResponse assembleResponse(@NonNull final Batch batch){
        return BatchResponse.builder()
                .status(batch.getBatchStatus())
                .batchCode(batch.getCode())
                .price(batch.getPrice())
                .expireIn(batch.getEstimatedTime())
                .build();
    }
}
