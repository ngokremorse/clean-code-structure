package com.vpbs.bpm.qtrr01.external.api.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MakerCancelTaskRequest {

    @NotEmpty
    private String processInstanceId;
    @NotEmpty
    private String taskId;
}
