package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepInfo {
    String stepTitle;
    Integer step;
    List<StepDto> info;
}

