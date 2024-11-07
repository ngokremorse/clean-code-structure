package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeregisterReq {
    String trackId;

    String[] signers;
}
