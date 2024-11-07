package com.vpbs.bpm.qtrr01.core.usecase;

import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.core.usecase.dto.SetViewersCommand;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SetViewerUseCase implements IUseCase<SetViewersCommand, String> {

    private final BpmService bpmService;

    @Override
    public String execute(SetViewersCommand request) {
        // validate
        bpmService.addViewers(request.getProcessInstanceId(), request.getViewers());
        return null;
    }
}
