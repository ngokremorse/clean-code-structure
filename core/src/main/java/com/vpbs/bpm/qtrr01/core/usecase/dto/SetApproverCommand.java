package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetApproverCommand {
    private String processInstanceId;
    private List<String> approvers;
}
