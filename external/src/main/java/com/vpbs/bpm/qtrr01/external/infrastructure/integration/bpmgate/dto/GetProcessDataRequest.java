package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GetProcessDataRequest {
    @NotEmpty
    private String processInstanceId;
    private String taskId;
    private String accessToken;
    private boolean fetchAll = true;
    @Schema(example = "[\"starter\", \"ipv4\"]")
    private List<String> fields;
}
