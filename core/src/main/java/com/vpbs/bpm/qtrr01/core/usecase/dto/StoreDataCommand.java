package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.Data;

import java.util.Map;

@Data
public class StoreDataCommand {
    private String processInstanceId;
    private Map<String, Object> processData;
}
