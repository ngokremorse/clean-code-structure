package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetViewersRequest {
    private String processInstanceId;
    private List<String> viewers;
}
