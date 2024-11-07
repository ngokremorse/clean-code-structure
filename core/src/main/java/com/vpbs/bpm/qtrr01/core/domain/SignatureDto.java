package com.vpbs.bpm.qtrr01.core.domain;

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
    @NotEmpty
    private String type;
    private Page pages;
    private String zone;
    private String zoneOriginal;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Page {
        private String type;
        private int[] pages;
    }
}
