package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetViewersCommand {
    private String accessToken;
    private String processInstanceId;
    private String taskId;
    private List<String> viewers;
}
