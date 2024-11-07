package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserRequest {
    private String accessToken;
    @NotEmpty
    private String username;
}
