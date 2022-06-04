package com.hencarvalho.freightsystem.infrastructure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BatchStatus {
    PENDING,
    ACTIVE,
    DRIVER_FOUND,
    ON_WAY,
    ON_DELIVERING,
    DONE
}
