package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto;
import com.vpbs.bpm.bond.external.infrastructure.integration.esign.enums.SignPageType;
import lombok.Data;

@Data
public class SignPage {
    private SignPageType type;
    private int[] pages;
}
