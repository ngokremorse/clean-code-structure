package com.vpbs.bpm.qtrr01.external.api;


import com.vpbs.bpm.qtrr01.external.infrastructure.service.ISecurityService;
import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;
import com.vpbs.bpm.qtrr01.core.usecase.MakerCancelTaskUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.MakerCompleteTaskUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.MakerCancelTaskCommand;
import com.vpbs.bpm.qtrr01.core.usecase.dto.MakerCompleteTaskCommand;
import com.vpbs.bpm.qtrr01.external.api.dto.MakerCancelTaskRequest;
import com.vpbs.bpm.qtrr01.external.api.dto.MakerCompleteTaskRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/api/task")
public class TaskRest {

    private final ISecurityService securityService;
    private final MakerCompleteTaskUseCase makerCompleteTaskUseCase;
    private final MakerCancelTaskUseCase makerCancelTaskUseCase;

    @RequestMapping(value = "/maker/complete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makerCompleteTask(@Valid @RequestBody MakerCompleteTaskRequest completeTaskRequest) throws ApiException {
        securityService.checkAccessTask(completeTaskRequest.getProcessInstanceId(), completeTaskRequest.getTaskId());

        MakerCompleteTaskCommand request = new MakerCompleteTaskCommand();
        BeanUtils.copyProperties(completeTaskRequest, request);
        request.setAccessToken(securityService.getAccessToken());

        makerCompleteTaskUseCase.execute(request);
    }

    @RequestMapping(value = "/maker/cancel", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makerCancelTask(@Valid @RequestBody MakerCancelTaskRequest makerCancelTaskRequest) throws ApiException {
        securityService.checkAccessTask(makerCancelTaskRequest.getProcessInstanceId(), makerCancelTaskRequest.getTaskId());

        MakerCancelTaskCommand request = new MakerCancelTaskCommand();
        BeanUtils.copyProperties(makerCancelTaskRequest, request);
        request.setAccessToken(securityService.getAccessToken());

        makerCancelTaskUseCase.execute(request);
    }
}
