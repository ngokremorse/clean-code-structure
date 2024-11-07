package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vpbs.bpm.bond.external.infrastructure.integration.esign.enums.SignatureType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DSignature {
    private SignatureType type;
    @JsonProperty("pages")
    private SignPage page;
    @Schema(description = "Chuỗi số theo thứ tự x-coordinate,y-coordinate,width,height với đơn vị là pt")
    private String zone;
}
