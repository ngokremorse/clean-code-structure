package com.vpbs.bpm.qtrr01.external.infrastructure.taskhandler;

import com.vpbs.bpm.qtrr01.core.usecase.service.EsignService;
import com.vpbs.bpm.qtrr01.external.infrastructure.utils.ExternalTaskUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

@Log4j2
@ExternalTaskSubscription(topicName = "completeTrack", processDefinitionKey = "QTRR_01")
@RequiredArgsConstructor
public class CompletedTrackingHandler implements ExternalTaskHandler {
    private final EsignService eSignService;

    @SneakyThrows
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        VariableMap variables = Variables.createVariables();

        // Không có trackId thì bỏ qua
        String trackId = externalTask.getVariable("trackId");
        String caseId = externalTask.getVariable("caseId");
        if (StringUtils.isEmpty(trackId)) {
            externalTaskService.complete(externalTask, variables);
            return;
        }
        try {
            var completeTrack = eSignService.completeTrack(trackId, null);
            log.info("{} completeTrack res: {}", caseId, completeTrack);
            if (!"200".equals(completeTrack.get("code"))) {
                throw new RuntimeException("Không hoàn thành được tracking | " + completeTrack.get("message"));
            }
            variables.put("trackingData", completeTrack.get("data"));
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
