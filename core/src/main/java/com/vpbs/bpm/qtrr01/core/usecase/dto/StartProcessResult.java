package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Data
public class StartProcessResult {

    private String processInstanceId;
    private String caseId;
    private Timestamp created;
    private Map<String, Object> others;
}
