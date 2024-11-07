package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BusinessDataDto {
    private @NotEmpty String processId;
    private @NotEmpty String taskId;
    @JsonProperty("CBNVAction")
    private String CBNVAction;
    @JsonProperty("test")
    private Object test;
    @JsonProperty("approveType")
    private String approveType;
    @JsonProperty("approvers")
    private Object approvers;
}
