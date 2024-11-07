package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetApproverRequest {
    @NotEmpty
    private String processInstanceId;
    @NotEmpty
    private String taskId;
    @NotNull
    @NotEmpty
    @Schema(description = "[\"thanhtq\"]")
    private List<String> approvers;
}
