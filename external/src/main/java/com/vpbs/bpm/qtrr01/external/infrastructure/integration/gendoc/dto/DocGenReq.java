package com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DocGenReq {
    private String templateId;
    private String caseId;
    private List<UserSignatures> userSignatures;
    private GenFileParamsRequest params;
    private List<String> fileViewers;

}
