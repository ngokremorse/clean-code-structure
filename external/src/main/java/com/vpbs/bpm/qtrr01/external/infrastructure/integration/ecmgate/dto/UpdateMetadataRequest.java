package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateMetadataRequest {
    private Map<String, Object> properties;
}