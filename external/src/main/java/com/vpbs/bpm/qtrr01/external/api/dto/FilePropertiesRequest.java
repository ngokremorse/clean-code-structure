package com.vpbs.bpm.qtrr01.external.api.dto;

import com.vpbs.bpm.qtrr01.core.enums.EcmDocumentCategoryEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FilePropertiesRequest {

    @NotNull
    private EcmDocumentCategoryEnum category;
    @NotEmpty
    private List<String> additionalUsers;
}
