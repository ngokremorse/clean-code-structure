package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepDto {
    String stepAssignee;
    Date doneDate;
    Date createDate;
    String fullName;
    String email;
    String status;
    String statusCode;
    String department;
}

