package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.Data;

import java.util.Map;

@Data
public class EcmDocumentDto {
    private String id;
    private String fileName;
    private Map<String, Object> properties;
}
