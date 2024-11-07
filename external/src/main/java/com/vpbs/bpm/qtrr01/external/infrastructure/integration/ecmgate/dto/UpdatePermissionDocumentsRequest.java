package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdatePermissionDocumentsRequest {
	private String ecmId;
	private List<String> accountList;
}