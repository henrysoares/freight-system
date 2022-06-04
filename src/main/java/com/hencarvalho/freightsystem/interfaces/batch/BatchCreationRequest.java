package com.hencarvalho.freightsystem.interfaces.batch;

import com.hencarvalho.freightsystem.domain.Address;
import com.hencarvalho.freightsystem.infrastructure.BatchType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatchCreationRequest implements Serializable {

    UUID customerCode;

    BatchType batchType;

    float batchWeight;

    int batchItems;

    Address destiny;

    String description;

}
