package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DNote {
    @JsonProperty("pages")
    private Page page;
    @Schema(description = "Chuỗi số theo thứ tự x-coordinate,y-coordinate,width,height với đơn vị là pt")
    private String zone;
    @Schema(description = "Vùng ký gốc: x,y,w,h")
    private String zoneOriginal;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Page {
        @Schema(description = "Loại ký: mặc định là custom")
        private String type;
        @Schema(description = "Trang ký")
        private int[] pages;
    }
}
