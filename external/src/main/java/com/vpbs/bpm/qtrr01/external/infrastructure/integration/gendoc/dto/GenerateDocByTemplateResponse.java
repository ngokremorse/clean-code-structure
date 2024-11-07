package com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateDocByTemplateResponse {
    private String code;
    private String message;
    private List<String> errors;
    private GenerateDocByTemplateDataResult data;
}
