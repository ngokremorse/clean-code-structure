package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MergeEcmFileToPdfCommand {

    private String fileType;
    private String group;
    private String outputFileName;
    private String caseId;
    private List<String> fileIds;
    private List<String> fileViewers;
    private Map<String, Object> config;
    private Map<String, String> metadata;

}
