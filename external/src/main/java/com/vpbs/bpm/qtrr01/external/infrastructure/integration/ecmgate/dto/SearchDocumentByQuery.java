package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto;

import com.vpbs.bpm.qtrr01.core.usecase.dto.DocumentCriteria;
import lombok.Data;

import java.util.List;

@Data
public class SearchDocumentByQuery {

    private String className;
    private List<DocumentCriteria> propertyCriteria;
    private List<String> propertyResponse;

}
