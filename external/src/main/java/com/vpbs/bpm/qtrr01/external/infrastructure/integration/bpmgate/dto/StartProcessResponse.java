package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Data
public class StartProcessResponse {

    private String processInstanceId;
    private String caseId;
    private Timestamp created;
    private Map<String, Object> others;
}
