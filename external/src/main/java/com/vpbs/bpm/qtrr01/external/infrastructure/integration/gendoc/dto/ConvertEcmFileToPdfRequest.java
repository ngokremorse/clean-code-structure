package com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ConvertEcmFileToPdfRequest {

    private String fileType;
    private String outputFileName;
    private List<String> fileIds;
    private String caseId;
    private List<String> fileViewers;
    private Map<String, Object> config;
    private Map<String, String> metadata;
    private String group;
}
