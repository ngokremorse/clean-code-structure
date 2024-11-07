package com.vpbs.bpm.qtrr01.core.usecase.service;


import com.vpbs.bpm.qtrr01.core.domain.TaskDto;
import com.vpbs.bpm.qtrr01.core.domain.UserDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StartProcessCommand;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StartProcessResult;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StoreDataCommand;

import java.util.List;
import java.util.Map;

public interface BpmService {

    StartProcessResult startProcess(StartProcessCommand startProcessCommand);

    void storeProcessData(StoreDataCommand storeDataCommand);

    void completeTask(String taskId, String accessToken, Map<String, Object> processData);

    List<TaskDto> getTasks(String processId);

    Map<String, Object> getProcessData(String processId);

    void setApprovers(String processInstance, List<String> users);

    void addViewers(String processInstance, List<String> users);

    List<UserDto> getAllUser(String accessToken);

    UserDto getUser(String accessToken, String userName);

}
