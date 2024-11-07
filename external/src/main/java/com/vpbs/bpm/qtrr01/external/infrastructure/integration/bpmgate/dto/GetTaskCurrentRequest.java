package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTaskCurrentRequest {

   @NotEmpty
   private String processInstanceId;
}
