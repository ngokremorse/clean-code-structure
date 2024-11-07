package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto;

import lombok.Data;

@Data
public class CreateFolderRequest{
	private String parentFolderPath;
	private String folderName;
}