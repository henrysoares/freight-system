package com.hencarvalho.freightsystem.application;

import com.hencarvalho.freightsystem.application.assemblers.BatchAssembler;
import com.hencarvalho.freightsystem.domain.repositories.BatchRepository;
import com.hencarvalho.freightsystem.domain.repositories.CustomerRepository;
import com.hencarvalho.freightsystem.infrastructure.BatchStatus;
import com.hencarvalho.freightsystem.interfaces.batch.BatchCreationRequest;
import com.hencarvalho.freightsystem.interfaces.batch.BatchResponse;
import lombok.AllArgsConstructor;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BatchService {

    BatchRepository batchRepository;

    CustomerRepository customerRepository;

    BatchAssembler assembler;

    public BatchResponse createBatch(@NonNull final BatchCreationRequest request){
        final var customer = customerRepository.findByCustomerCode(request.getCustomerCode().toString()).get();

        final var batch = assembler.assemble(request, customer);

        batchRepository.save(batch);

        return assembler.assembleResponse(batch);
    }

    public void confirm(@NonNull final UUID batchCode){
        final var batch = batchRepository.findByCode(batchCode.toString()).get();

        batch.setBatchStatus(BatchStatus.ACTIVE);

        batchRepository.save(batch);
    }

}
