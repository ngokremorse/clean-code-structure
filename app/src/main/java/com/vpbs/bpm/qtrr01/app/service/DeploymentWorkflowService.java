package com.vpbs.bpm.qtrr01.app.service;


import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.BpmGateService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.CustomMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Log4j2
public class DeploymentWorkflowService {
    private final BpmGateService bpmGateService;

    @EventListener(ApplicationReadyEvent.class)
    public void deployBPMN() throws IOException {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] bpmnResources = resolver.getResources("*.bpmn");
            for (Resource bpmnResource : bpmnResources) {
                MultipartFile multipartFile = new CustomMultipartFile(bpmnResource.getContentAsByteArray(), bpmnResource.getFilename());
                log.info("Start deploy bpmn file: {}", bpmnResource.getFilename());
                bpmGateService.deployBPMN(multipartFile);
                log.info("deployed bpmn file: {}", bpmnResource.getFilename());
            }
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
        }
    }
}