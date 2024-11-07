package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import lombok.Data;

import java.util.Map;

@Data
public class StartProcessRequest {
	private Map<String, Object> processData;
	private String caseId;
	private String processKey;
}