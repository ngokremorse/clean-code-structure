package com.vpbs.bpm.qtrr01.external.api.dto;

import com.vpbs.bpm.qtrr01.core.enums.EcmDocumentCategoryEnum;
import com.vpbs.bpm.qtrr01.core.enums.EcmPropertiesEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SearchDocumentRequest {

    private String caseId;
    private String processId;
    private EcmDocumentCategoryEnum category;
    @NotNull
    private List<EcmPropertiesEnum> propertyAttributes;
}
