package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserQuery {

    private String accessToken;
    private String userName;
}
