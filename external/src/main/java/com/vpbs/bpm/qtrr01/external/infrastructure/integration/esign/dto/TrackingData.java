package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingData {
    String finalFileName;
    String finalFileId;
    String auditFileName;
    String auditFileId;
    String mergedFileId;
}
