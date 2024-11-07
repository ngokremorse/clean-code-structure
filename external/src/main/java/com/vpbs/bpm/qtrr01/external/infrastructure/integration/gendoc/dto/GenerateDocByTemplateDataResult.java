package com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateDocByTemplateDataResult {
    private String generatedFileId;
    private String generatedFilename;
    private String sign;
}
