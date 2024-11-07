package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.Data;

import java.util.List;

@Data
public class MakerCompleteTaskCommand {
    private String accessToken;
    private String processInstanceId;
    private String taskId;
    private List<String> viewers;
    private List<String> approvers;
}
