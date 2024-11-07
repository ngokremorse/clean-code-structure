package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {

    private String id;
    private String name;
    private String assignee;
    private String taskCode;
    
    
}
