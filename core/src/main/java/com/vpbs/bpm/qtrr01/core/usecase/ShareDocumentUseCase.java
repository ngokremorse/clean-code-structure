package com.vpbs.bpm.qtrr01.core.usecase;

import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.ShareDocumentCommand;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.core.usecase.service.EcmService;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

@UseCase
@RequiredArgsConstructor
public class ShareDocumentUseCase implements IUseCase<ShareDocumentCommand, String> {

    private final BpmService bpmService;
    private final EcmService ecmService;

    @Override

    public String execute(ShareDocumentCommand request) {
        ecmService.updatePermissionDocumentsByProcessId(request.getProcessId(), request.getUserName());
        bpmService.addViewers(request.getProcessId(), Collections.singletonList(request.getUserName()));
        return null;
    }
}
