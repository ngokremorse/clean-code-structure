package com.vpbs.bpm.qtrr01.core.usecase.dto;

import com.vpbs.bpm.qtrr01.core.enums.EcmPropertiesEnum;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Builder
@Data
public class EcmProperties {

    private String caseId;
    private String processInstanceId;

    private String category;

    public HashMap<String, String> toHashmap() {
        HashMap<String, String> attribute = new HashMap<>();
        attribute.put(EcmPropertiesEnum.caseId.name(), caseId);
        attribute.put(EcmPropertiesEnum.ProcessInstanceID.name(), processInstanceId);
        attribute.put(EcmPropertiesEnum.DocumentCategory.name(), category);

        return attribute;
    }
}
