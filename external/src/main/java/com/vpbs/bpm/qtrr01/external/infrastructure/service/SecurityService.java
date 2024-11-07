package com.vpbs.bpm.qtrr01.external.infrastructure.service;


import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;
import com.vpbs.bpm.qtrr01.core.domain.TaskDto;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.external.infrastructure.dto.XUserInfo;
import com.vpbs.bpm.qtrr01.external.infrastructure.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Utility class for Spring Security.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService implements ISecurityService {


    private final BpmService bpmService;

    private final HttpServletRequest httpServletRequest;

    private XUserInfo getXUserInfo() {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return SecurityUtils.getXUserInfo(token);
    }

    @Override
    public String getUsername() {
        XUserInfo xUserInfo = getXUserInfo();
        return xUserInfo != null ? xUserInfo.getPreferredUsername() : null;
    }

    @Override
    public String getAccessToken() {
        return httpServletRequest.getHeader("Authorization").substring("Bearer ".length());
    }

    @Override
    public void checkAccessProcess(String processId) throws ApiException {
        List<TaskDto> tasks = bpmService.getTasks(processId);
        if (tasks.stream().noneMatch(item -> item.getAssignee().equals(getUsername()))) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Forbiden", "Bạn không có quyền thực hiện!");
        }
    }

    @Override
    public void checkAccessTask(String processId, String taskId) throws ApiException {
        List<TaskDto> tasks = bpmService.getTasks(processId);
        if (tasks.stream().noneMatch(item -> item.getAssignee().equals(getUsername()) && item.getId().equals(taskId))) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Forbiden", "Bạn không có quyền thực hiện!");
        }
    }
}

