package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.Data;

import java.util.Map;

@Data
public class StartProcessCommand {

    private Map<String, Object> processData;
    private String caseId;
    private String processKey;

}
