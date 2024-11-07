package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmhub.dto;

import lombok.Data;

@Data
public class SubscribersItem{
	private boolean disablePushInapp;
	private String mobile;
	private String userId;
	private String email;
}