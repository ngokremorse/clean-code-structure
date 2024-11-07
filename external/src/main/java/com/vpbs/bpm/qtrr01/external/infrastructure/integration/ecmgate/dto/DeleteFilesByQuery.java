package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto;

import lombok.Data;

@Data
public class DeleteFilesByQuery {
	private String propertyName;
	private String className;
	private String propertyValue;
}