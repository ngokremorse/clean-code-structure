package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignerDto {
    private String signerId;
    private Integer level;
}
