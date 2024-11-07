package com.vpbs.bpm.qtrr01.core.usecase.service;

import com.vpbs.bpm.qtrr01.core.usecase.dto.*;

import java.util.Map;

public interface EsignService {
    Map<String, String> createTrackId(CreateTrackIdCommand request);
    Map<String, Object> completeTrack(String trackId, String closeReason);

}
