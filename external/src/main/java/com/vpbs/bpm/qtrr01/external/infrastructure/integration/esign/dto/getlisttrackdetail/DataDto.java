package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.getlisttrackdetail;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DataDto{
	private String processId;
	private AuthorDto author;
	private List<DetailsItemDto> details;
	private Map<String, String> currentFiles;
	private LastSignerDto lastSigner;
	private String mergedFileId;
}