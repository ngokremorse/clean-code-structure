package com.vpbs.bpm.qtrr01.core.usecase.dto;


import lombok.Data;

@Data
public class MakerCancelTaskCommand {
    private String accessToken;
    private String processInstanceId;
    private String taskId;
}
