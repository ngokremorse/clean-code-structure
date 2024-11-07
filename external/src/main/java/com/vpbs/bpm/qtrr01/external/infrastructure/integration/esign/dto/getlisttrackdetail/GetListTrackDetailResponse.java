package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.getlisttrackdetail;

import lombok.Data;

import java.util.List;

@Data
public class GetListTrackDetailResponse{
	private String code;
	private DataDto data;
	private String message;
	private List<String> errors;
}