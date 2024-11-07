package com.vpbs.bpm.qtrr01.external.infrastructure.taskhandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpbs.bpm.qtrr01.core.usecase.dto.CreateTrackIdCommand;
import com.vpbs.bpm.qtrr01.core.usecase.service.EsignService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.Approver;
import com.vpbs.bpm.qtrr01.external.infrastructure.utils.ExternalTaskUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Log4j2
@ExternalTaskSubscription(topicName = "createTrackId", processDefinitionKey = "QTRR_01")
@RequiredArgsConstructor
public class CreateTrackIdHandler implements ExternalTaskHandler {
    private final EsignService eSignService;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String processInstanceId = externalTask.getProcessInstanceId();
        String originator = externalTask.getVariable("originator");
        String processName = externalTask.getVariable("processName");
        String starterIp = externalTask.getVariable("starterIp");
        String caseId = externalTask.getVariable("caseId");

        // Tính toán những người có thể xem file
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Approver> singers = objectMapper.convertValue(externalTask.getVariable("approvers"), new TypeReference<>() {});
        Object listViewerVariable = externalTask.getVariable("viewers");
        String viewers = null;
        if(listViewerVariable != null) {
            List<String> listViewer = objectMapper.convertValue(listViewerVariable, new TypeReference<>() {});
            viewers = String.join(",", listViewer);
        }
        CreateTrackIdCommand createTrackIdRequest = CreateTrackIdCommand.builder()
                .processId(processInstanceId)
                .caseId(caseId)
                .authorId(originator)
                .title(processName)
                .authorIp(starterIp)
                .signers(String.join(",", singers.stream().map(Approver::getAcc).toList()))
                .viewers(viewers)
                .build();
        try {
            log.info("{} Create tracking data request: {} ", caseId, createTrackIdRequest);
            Map<String, String> trackData = eSignService.createTrackId(createTrackIdRequest);
            log.info("{} Create tracking data res: {}", caseId, trackData);
            if (!"200".equals(trackData.get("code"))) {
                throw new RuntimeException("Không tạo được trackId | " + trackData.get("message"));
            }
            VariableMap variables = Variables.createVariables();
            variables.put("trackId", trackData.get("data"));
            externalTaskService.complete(externalTask, variables);
        } catch (Exception e) {
            log.error(e);
            externalTaskService.handleFailure(externalTask,
                    externalTask.getWorkerId(),
                    e.getLocalizedMessage(),
                    ExternalTaskUtils.getRetries(externalTask, 3),
                    60000);
        }
    }
}