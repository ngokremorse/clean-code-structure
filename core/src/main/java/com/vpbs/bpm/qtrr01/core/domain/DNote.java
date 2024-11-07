package com.vpbs.bpm.qtrr01.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DNote {
    @JsonProperty("pages")
    private Page page;
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
