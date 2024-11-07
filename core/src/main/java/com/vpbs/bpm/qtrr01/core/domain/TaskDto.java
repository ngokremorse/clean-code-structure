package com.vpbs.bpm.qtrr01.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private String id;
    private String name;
    private String assignee;
    private String taskCode;
}
