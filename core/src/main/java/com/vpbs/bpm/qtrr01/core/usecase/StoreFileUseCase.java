package com.vpbs.bpm.qtrr01.core.usecase;


import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StoreFileCommand;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.core.usecase.service.EcmService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class StoreFileUseCase implements IUseCase<StoreFileCommand, String> {
    private final EcmService ecmService;
    private final BpmService bpmService;

    @Override
    public String execute(StoreFileCommand request) {
        Map<String, Object> processData = bpmService.getProcessData(request.getProperties().getProcessInstanceId());
        String caseId = processData.get("caseId").toString();
        request.getProperties().setCaseId(caseId);
        return ecmService.uploadDocument(request.getFile(), request.getAdditionalUsers(), request.getProperties());
    }
}
