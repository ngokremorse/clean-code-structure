package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserRequest {
    private String accessToken;
}
