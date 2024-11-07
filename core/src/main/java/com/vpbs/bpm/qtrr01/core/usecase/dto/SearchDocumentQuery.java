package com.vpbs.bpm.qtrr01.core.usecase.dto;

import com.vpbs.bpm.qtrr01.core.enums.EcmDocumentCategoryEnum;
import com.vpbs.bpm.qtrr01.core.enums.EcmPropertiesEnum;
import lombok.Data;

import java.util.List;

@Data
public class SearchDocumentQuery {
    private String processId;
    private String caseId;
    private EcmDocumentCategoryEnum category;
    private List<EcmPropertiesEnum> propertyAttributes;
}
