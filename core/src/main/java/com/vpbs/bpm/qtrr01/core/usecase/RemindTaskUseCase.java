package com.vpbs.bpm.qtrr01.core.usecase;

import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.StoreDataCommand;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmHubService;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class RemindTaskUseCase implements IUseCase<String, String> {
    private final BpmHubService BpmHubService;
    private final BpmService bpmService;

    @Override
    public String execute(String processId) throws ApiException {
        // kiểm kiểm tra thời điểm nhắc remind
        Map<String, Object> processData = bpmService.getProcessData(processId);
        if (processData.containsKey("lastRemind") && (long) (processData.get("lastRemind")) > System.currentTimeMillis() - 86400000) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Please stay calm", "Clicking at high speed is not good for your arms");
        }

        // send notify
        BpmHubService.remindTaskForChecker(processId);

        // update time remind
        StoreDataCommand storeDataCommand = new StoreDataCommand();
        storeDataCommand.setProcessInstanceId(processId);
        // process data
        Map<String, Object> dataStore = new HashMap<>();
        dataStore.put("lastRemind", System.currentTimeMillis());
        storeDataCommand.setProcessData(dataStore);
        bpmService.storeProcessData(storeDataCommand);
        return null;
    }
}
