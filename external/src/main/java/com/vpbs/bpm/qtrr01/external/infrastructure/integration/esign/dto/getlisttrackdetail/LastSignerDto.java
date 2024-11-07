package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.getlisttrackdetail;

import lombok.Data;

@Data
public class LastSignerDto {
	private String ipAddress;
	private String fullName;
	private String id;
	private String email;
	private int signTimestamp;
}