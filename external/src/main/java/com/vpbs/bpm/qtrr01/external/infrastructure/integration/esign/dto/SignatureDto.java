package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignatureDto {
    @Schema(description = "primary, mark")
    @NotEmpty
    private String type;
    @Schema(description = "Thông tin trang ký")
    private Page pages;
    @Schema(description = "Vùng ký chuyển đổi: x,y,w,h")
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
