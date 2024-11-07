package com.vpbs.bpm.qtrr01.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentSignDto {
    private String userId;

    private Integer level;

    private String fileVariableName; // File thực hiện ký

    private List<SignatureDto> signatures; // Các chữ ký

    private List<DNote> notes; // Các text

    private List<Map<String, Object>> others; // Các thông tin khác
}
