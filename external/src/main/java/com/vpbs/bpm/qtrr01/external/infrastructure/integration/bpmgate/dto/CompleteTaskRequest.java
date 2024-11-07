package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteTaskRequest {

    @NotEmpty
    private String taskId;
    private String accessToken;
    @Schema(example = "{\"starter\":\"thanhtq\"}")
    private Map<String, Object> processData;
}
