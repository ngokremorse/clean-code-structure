package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmhub.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotificationRequest {
	private String code;
	private Long createdDate;
	private Map<String, Object> data;
	private List<String> channels;
	private List<SubscribersItem> subscribers;
	private String appCode;
	private String source;
	private String taskId;
}