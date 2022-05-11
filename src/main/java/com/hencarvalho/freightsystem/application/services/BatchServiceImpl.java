package com.hencarvalho.freightsystem.application.services;

import com.hencarvalho.freightsystem.interfaces.batch.BatchCreationRequest;
import com.hencarvalho.freightsystem.interfaces.batch.dto.BatchDTO;
import lombok.NonNull;

public class BatchServiceImpl implements BatchService{
    @Override
    public BatchDTO createBatch(@NonNull BatchCreationRequest request) {
        return null;
    }
}
