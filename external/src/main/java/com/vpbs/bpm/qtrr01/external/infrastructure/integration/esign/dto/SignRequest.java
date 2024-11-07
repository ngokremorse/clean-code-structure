package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignRequest {
    @NotEmpty(message = "Task id không được để trống!")
    private String taskId;
    @NotNull(message = "Level phải là số!")
    private Integer level;
}
