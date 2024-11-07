package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ESignDto {
    private String userId;
    private String trackId;
    private List<Document> documents;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Document {
        private String path;
        private List<SignatureDto> signatures;
        private List<DNote> notes;
        private boolean skipMerge = false;
    }

}
