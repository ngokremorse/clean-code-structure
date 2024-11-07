package com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate;

import com.vpbs.bpm.qtrr01.external.config.FeignClientConfig;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.bpmgate.dto.*;
import feign.Headers;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Call sang api của BPM GATE
 */
@FeignClient(value = "bpmGateService", url = "${services.bpmGate.uri}/v1/api", configuration = FeignClientConfig.class)
public interface BpmGateService {

    @Headers("Content-Type: multipart/form-data")
    @RequestMapping(method = RequestMethod.POST, value = "/deployment/bpmn", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void deployBPMN(@RequestPart("file") MultipartFile file);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/process/start")
    StartProcessResponse startProcess(@Valid @RequestBody StartProcessRequest startProcessRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/process/save")
    void storeProcessData(@Valid @RequestBody StoreProcessDataRequest storeProcessDataRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/process/data")
    Map<String, Object> getProcessData(@Valid @RequestBody GetProcessDataRequest getProcessDataRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/task/complete")
    void completeTask(@Valid @RequestBody CompleteTaskRequest completeTaskRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/query/user/get")
    UserGroupDto getUser(@Valid @RequestBody GetUserRequest request);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/query/user/all")
    List<UserGroupDto> getUserAll(@Valid @RequestBody GetAllUserRequest request);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/process/viewers")
    void setViewers(@Valid @RequestBody SetViewersRequest request);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/process/setApprovers")
    void setApprovers(@Valid @RequestBody SetApproverRequest request);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/task")
    List<TaskResponse> getTasksCurrent(@Valid @RequestBody GetTaskCurrentRequest request);

}
