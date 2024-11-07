package com.vpbs.bpm.qtrr01.core.usecase;

import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StartProcessCommand;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StartProcessResult;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.core.usecase.service.CaseIdLatestService;
import com.vpbs.bpm.qtrr01.core.usecase.service.EcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class StartProcessUseCase implements IUseCase<StartProcessCommand, StartProcessResult> {

    private final BpmService bpmService;
    private final EcmService ecmService;
    private final CaseIdLatestService caseIdLatestService;

    @Value("${services.ecmGate.baseFolder}")
    private String baseFolder;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public StartProcessResult execute(StartProcessCommand createBonUseCaseRequest) {
        String caseId = caseIdLatestService.generateCaseId();
        createBonUseCaseRequest.setCaseId(caseId);
        StartProcessResult startProcessResponse =  bpmService.startProcess(createBonUseCaseRequest);
        if(startProcessResponse.getProcessInstanceId() != null) {
            ecmService.createFolder(caseId, baseFolder);
        }
        return startProcessResponse;
    }
}
