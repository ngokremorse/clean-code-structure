package com.vpbs.bpm.qtrr01.external.infrastructure.service;

import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;

public interface ISecurityService {

    String getUsername();
    String getAccessToken();
    void checkAccessProcess(String processId) throws ApiException;
    void checkAccessTask(String processId, String taskId) throws ApiException;
}
