package com.vpbs.bpm.qtrr01.external.infrastructure.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmHubService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.BpmGateService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto.GetProcessDataRequest;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmhub.BpmHubGateService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmhub.dto.EmailNotificationRequest;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmhub.dto.SubscribersItem;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.Approver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BpmHubImplService implements BpmHubService {
    private final BpmHubGateService bpmHubGateService;
    private final BpmGateService bpmGateService;
    @Value("${email.app-code}")
    private String mailAppCode;

    @Value("${email.source}")
    private String mailSource;
    @Value("${email.remind-code}")
    private String remindCode;

    public void sendNotify(String mailCode, List<String> emails, Map<String, Object> params) {

        List<SubscribersItem> subscribersItems = emails.stream().map(item -> {
            SubscribersItem subscribersItem = new SubscribersItem();
            subscribersItem.setEmail(item);
            return subscribersItem;
        }).toList();

        EmailNotificationRequest request = EmailNotificationRequest
                .builder()
                .code(mailCode)
                .source(mailSource)
                .appCode(mailAppCode)
                .subscribers(subscribersItems)
                .createdDate(System.currentTimeMillis())
                .data(params)
                .build();

        bpmHubGateService.notification(request);
    }

    @Override
    public void remindTaskForChecker(String processId) {
        // lấy email checker
        GetProcessDataRequest getProcessDataRequest = new GetProcessDataRequest();
        getProcessDataRequest.setProcessInstanceId(processId);
        getProcessDataRequest.setFields(List.of("approvers"));
        getProcessDataRequest.setFetchAll(false);
        Map<String, Object> processData = bpmGateService.getProcessData(getProcessDataRequest);
        List<Approver> approvers = new ObjectMapper().convertValue(processData.get("approvers"), new TypeReference<>() {
        });
        List<String> emails = approvers.stream().map(item -> item.getAcc() + "@vpbanks.com.vn").toList();

        // build data
        Map<String, Object> params = new HashMap<>();

        sendNotify(remindCode, emails, params);
    }

}
