package com.vpbs.bpm.qtrr01.external.infrastructure.taskhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

@Log4j2
@ExternalTaskSubscription(topicName = "mail03", processDefinitionKey = "QTRR_01")
@RequiredArgsConstructor
public class Mail03Handler implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

    }
}