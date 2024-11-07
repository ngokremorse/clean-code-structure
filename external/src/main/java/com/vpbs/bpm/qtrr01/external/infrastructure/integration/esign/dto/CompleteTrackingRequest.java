package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import lombok.Data;

@Data
public class CompleteTrackingRequest {
    private String trackId;
    private String closeReason;
}
