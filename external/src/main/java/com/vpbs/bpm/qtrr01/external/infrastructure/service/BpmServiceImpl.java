package com.vpbs.bpm.qtrr01.external.infrastructure.service;

import com.vpbs.bpm.qtrr01.core.domain.TaskDto;
import com.vpbs.bpm.qtrr01.core.domain.UserDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StartProcessCommand;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StartProcessResult;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StoreDataCommand;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.BpmGateService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto.*;
import com.vpbs.bpm.qtrr01.external.infrastructure.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BpmServiceImpl implements BpmService {

    private final BpmGateService bpmGateService;
    private final HttpServletRequest httpServletRequest;

    @Override
    public StartProcessResult startProcess(StartProcessCommand startProcessCommand) {
        StartProcessRequest startProcessRequest = new StartProcessRequest();
        BeanUtils.copyProperties(startProcessCommand, startProcessRequest);
        startProcessRequest.getProcessData().put("caseId", startProcessCommand.getCaseId());
        startProcessRequest.getProcessData().put("starter", SecurityUtils.getUsername(SecurityUtils.getAccessToken(httpServletRequest)));
        StartProcessResponse startProcessResponse = bpmGateService.startProcess(startProcessRequest);

        StartProcessResult startProcessResult = new StartProcessResult();
        BeanUtils.copyProperties(startProcessResponse, startProcessResult);
        return startProcessResult;
    }

    @Override
    public void storeProcessData(StoreDataCommand storeDataCommand) {
        StoreProcessDataRequest request = new StoreProcessDataRequest();
        BeanUtils.copyProperties(storeDataCommand, request);
        bpmGateService.storeProcessData(request);
    }

    @Override
    public void completeTask(String taskId, String accessToken, Map<String, Object> processData) {
        CompleteTaskRequest completeTaskRequest = new CompleteTaskRequest();
        completeTaskRequest.setTaskId(taskId);
        completeTaskRequest.setProcessData(processData);
        completeTaskRequest.setAccessToken(accessToken);
        bpmGateService.completeTask(completeTaskRequest);
    }

    @Override
    public List<TaskDto> getTasks(String processId) {
        GetTaskCurrentRequest request = new GetTaskCurrentRequest();
        request.setProcessInstanceId(processId);
        return bpmGateService.getTasksCurrent(request).stream().map(item -> {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(item, taskDto);
            return taskDto;
        }).toList();
    }

    @Override
    public Map<String, Object> getProcessData(String processId) {
        GetProcessDataRequest getProcessDataRequest = new GetProcessDataRequest();
        getProcessDataRequest.setProcessInstanceId(processId);
        return bpmGateService.getProcessData(getProcessDataRequest);
    }

    @Override
    public void setApprovers(String processInstance, List<String> users) {
        SetApproverRequest request = new SetApproverRequest();
        request.setProcessInstanceId(processInstance);
        request.setApprovers(users);
        bpmGateService.setApprovers(request);
    }

    @Override
    public void addViewers(String processInstance, List<String> users) {
        SetViewersRequest request = new SetViewersRequest();
        request.setProcessInstanceId(processInstance);
        request.setViewers(users);
        bpmGateService.setViewers(request);
    }

    @Override
    public List<UserDto> getAllUser(String accessToken) {
        GetAllUserRequest getAllUserRequest = new GetAllUserRequest();
        getAllUserRequest.setAccessToken(accessToken);
        return bpmGateService.getUserAll(getAllUserRequest).stream().map(item -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(item, userDto);
            return userDto;
        }).toList();
    }

    @Override
    public UserDto getUser(String accessToken, String userName) {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setAccessToken(accessToken);
        getUserRequest.setUsername(userName);
        UserGroupDto user = bpmGateService.getUser(getUserRequest);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}
