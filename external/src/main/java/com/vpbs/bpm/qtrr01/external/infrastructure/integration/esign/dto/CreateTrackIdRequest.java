package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTrackIdRequest {
    private String processId;
    private String authorId; // userId
    private String authorIp;
    private String title;
    private String flowType;
    private String caseId;
    private String signers;
    private String viewers;
}
