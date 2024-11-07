package com.vpbs.bpm.qtrr01.external.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class Account {
    private List<String> roles;
}