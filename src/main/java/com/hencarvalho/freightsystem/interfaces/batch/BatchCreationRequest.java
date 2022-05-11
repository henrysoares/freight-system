package com.hencarvalho.freightsystem.interfaces.batch;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BatchCreationRequest implements Serializable {
    private static final long serialVersionUID = -1167632864123199382L;

    @NotNull
    String description;

    float weight;


}
