package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmhub;

import com.vpbs.bpm.qtrr01.external.config.FeignClientConfig;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmhub.dto.EmailNotificationRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * Call sang api của BPM GATE
 */
@FeignClient(value = "bpmHubService", url = "${services.bpmHub.uri}", configuration = FeignClientConfig.class)
public interface BpmHubGateService {

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/notification/email")
    void notification(@RequestBody EmailNotificationRequest request);
}
