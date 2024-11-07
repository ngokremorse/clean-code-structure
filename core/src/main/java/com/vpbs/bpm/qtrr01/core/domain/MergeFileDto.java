package com.vpbs.bpm.qtrr01.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MergeFileDto {
    List<String> fileIds;
    String outputFileName;
    private List<String> fileViewers;
    private String group;
    private String caseId;
}
