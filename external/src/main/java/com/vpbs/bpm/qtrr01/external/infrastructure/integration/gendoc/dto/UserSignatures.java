package com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignatures {
    private String userId;
    private String userRole;
}
