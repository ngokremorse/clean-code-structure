package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UploadFileRequest{
	@JsonProperty("baseFolder")
	private String baseFolder;
	@JsonProperty("className")
	private String className;
	@JsonProperty("additionalUsers")
	private List<String> additionalUsers;
	@JsonProperty("properties")
	private Map<String, String> properties;
}