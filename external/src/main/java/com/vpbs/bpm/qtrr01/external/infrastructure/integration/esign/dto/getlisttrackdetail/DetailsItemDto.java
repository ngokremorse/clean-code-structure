package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.getlisttrackdetail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailsItemDto {
	private String ipAddress;
	private String fullName;
	private String id;
	private String email;
	private int signTimestamp;
	private Integer level;
}