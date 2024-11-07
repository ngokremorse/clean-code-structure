package com.vpbs.bpm.qtrr01.external.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ShareDocumentRequest {
    @NotEmpty
    private String processId;
    @NotEmpty
    private String userName;
}
