package com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign;

import com.vpbs.bpm.qtrr01.external.config.FeignClientConfig;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.*;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.getlisttrackdetail.GetListTrackDetailResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "eSign", url = "${services.esignGate.uri}", configuration = FeignClientConfig.class)
public interface ESignGateService {
    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/tracking/createRequest")
    Map<String, String> createTrackId(@RequestBody CreateTrackIdRequest createTrackIdRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/signatureRequest/register")
    Map<String, Object> registerSign(@RequestBody ESignDto eSignDto);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/tracking/completeTrack")
    Map<String, Object> completeTrack(@RequestBody CompleteTrackingRequest completeTrackingRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.GET, value = "/tracking/{trackId}")
    GetListTrackDetailResponse getTrackDetail(@PathVariable String trackId);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/signatureRequest/complete")
    Map<String, Object> completeSign(@RequestBody SignCompleteRequest completeRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/tracking/updateSigners")
    Map<String, Object> updateSigners(@RequestBody DeregisterReq deregisterReq);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.GET, value = "/tracking/transientSigned/{trackId}")
    Map<String, Object> getTransientSigners(@PathVariable String trackId);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.GET, value = "/tracking/{trackId}/getTransientFile")
    Map<String, Object> getTransientFile(@PathVariable String trackId);
}
