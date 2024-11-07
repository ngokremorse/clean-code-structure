package com.vpbs.bpm.qtrr01.core.usecase;


import com.vpbs.bpm.qtrr01.core.domain.ProcessData;
import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;
import com.vpbs.bpm.qtrr01.core.enums.MakerActionEnum;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.MakerCancelTaskCommand;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class MakerCancelTaskUseCase implements IUseCase<MakerCancelTaskCommand, String> {
    private final BpmService bpmService;

    @Override
    @Transactional
    public String execute(MakerCancelTaskCommand request) throws ApiException {

        ProcessData processData = new ProcessData();
        processData.setMakerAction(MakerActionEnum.cancel);

        try {
            bpmService.completeTask(request.getTaskId(), request.getAccessToken(), processData.toMap());
        } catch (IllegalAccessException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid data", e.getMessage());
        }
        return null;
    }
}
