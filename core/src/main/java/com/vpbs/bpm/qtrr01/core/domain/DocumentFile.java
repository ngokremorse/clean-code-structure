package com.vpbs.bpm.qtrr01.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentFile {
    private String fileName;
    private String fileUrl;
}
