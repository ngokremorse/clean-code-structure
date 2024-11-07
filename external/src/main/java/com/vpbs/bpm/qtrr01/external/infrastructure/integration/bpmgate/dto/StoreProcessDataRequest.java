package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Map;

@Data
public class StoreProcessDataRequest {
    @NotEmpty
    private String processInstanceId;
    @NotEmpty
    @Schema(example = "{\"subProcess\":\"customer\"}")
    private Map<String, Object> processData;
}
