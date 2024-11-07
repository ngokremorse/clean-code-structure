package com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto;

import lombok.Data;

import java.util.List;

@Data
public class MergeFilesRequest {
    List<String> fileIds;
    String outputFileName;
    private List<String> fileViewers;
    private String group;
    private String caseId;
}
