package com.vpbs.bpm.qtrr01.external.infrastructure.service;

import com.vpbs.bpm.qtrr01.core.usecase.dto.CreateTrackIdCommand;
import com.vpbs.bpm.qtrr01.core.usecase.service.EsignService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.ESignGateService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.CompleteTrackingRequest;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.CreateTrackIdRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EsignServiceImpl implements EsignService {
    private final ESignGateService eSignGateService;

    @Override
    public Map<String, String> createTrackId(CreateTrackIdCommand request) {

        CreateTrackIdRequest createTrackIdRequest = new CreateTrackIdRequest();
        BeanUtils.copyProperties(request, createTrackIdRequest);

        return eSignGateService.createTrackId(createTrackIdRequest);
    }

    @Override
    public Map<String, Object> completeTrack(String trackId, String closeReason) {
        CompleteTrackingRequest completeTrackingRequest = new CompleteTrackingRequest();
        completeTrackingRequest.setTrackId(trackId);
        completeTrackingRequest.setCloseReason(closeReason);
        return eSignGateService.completeTrack(completeTrackingRequest);
    }
}
