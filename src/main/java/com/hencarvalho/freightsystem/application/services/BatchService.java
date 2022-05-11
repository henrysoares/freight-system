package com.hencarvalho.freightsystem.application.services;

import com.hencarvalho.freightsystem.interfaces.batch.BatchCreationRequest;
import com.hencarvalho.freightsystem.interfaces.batch.dto.BatchDTO;
import lombok.NonNull;

public interface BatchService {

    BatchDTO createBatch(@NonNull final BatchCreationRequest request);

}
