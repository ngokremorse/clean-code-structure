package com.vpbs.bpm.qtrr01.core.usecase;


import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.service.EcmService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DeleteFileUseCase implements IUseCase<List<String>, String> {
    private final EcmService ecmService;

    @Override
    public String execute(List<String> fileIds) {
        ecmService.deleteDocuments(fileIds);
        return null;
    }
}