package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckFolderExistsRequest {
    @NotEmpty
    private String folderPath;
}
