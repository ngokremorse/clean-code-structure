package com.vpbs.bpm.qtrr01.external.api.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class MakerCompleteTaskRequest {

    @NotEmpty
    private String processInstanceId;
    @NotEmpty
    private String taskId;
    private List<String> viewers;
    @NotEmpty
    private List<String> approvers;
}
