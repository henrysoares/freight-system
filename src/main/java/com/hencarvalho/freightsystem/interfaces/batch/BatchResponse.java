package com.hencarvalho.freightsystem.interfaces.batch;

import com.hencarvalho.freightsystem.infrastructure.BatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class BatchResponse implements Serializable {
    BatchStatus status;
    float price;
    String batchCode;
    int expireIn;
}
