package com.vpbs.bpm.qtrr01.core.usecase;

import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.core.usecase.dto.SetApproverCommand;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SetApproverUseCase implements IUseCase<SetApproverCommand, String> {

    private final BpmService bpmService;

    @Override
    public String execute(SetApproverCommand request) {
        // validate
        bpmService.setApprovers(request.getProcessInstanceId(), request.getApprovers());
        return null;
    }
}
