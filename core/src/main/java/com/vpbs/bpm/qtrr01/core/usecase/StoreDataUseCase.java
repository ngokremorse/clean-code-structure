package com.vpbs.bpm.qtrr01.core.usecase;

import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StoreDataCommand;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class StoreDataUseCase implements IUseCase<StoreDataCommand, String> {
    private final BpmService bpmService;

    @Override
    public String execute(StoreDataCommand request) {
        bpmService.storeProcessData(request);
        return null;
    }
}
