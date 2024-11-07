package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto;

import java.util.List;
import lombok.Data;

@Data
public class UpdatePermissionsRequest {
	private List<String> accountList;
}