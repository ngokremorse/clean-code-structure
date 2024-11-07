package com.vpbs.bpm.qtrr01.external.api;


import com.vpbs.bpm.qtrr01.core.domain.UserDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.GetUserQuery;
import com.vpbs.bpm.qtrr01.core.usecase.GetAllUserUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.GetUserUseCase;
import com.vpbs.bpm.qtrr01.external.infrastructure.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/api/user")
public class UserRest {
    private final GetAllUserUseCase getAllUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final HttpServletRequest httpServletRequest;

    @GetMapping("/all")
    List<UserDto> getAllUser() {
        String accessToken = SecurityUtils.getAccessToken(httpServletRequest);
        return getAllUserUseCase.execute(accessToken);
    }

    @GetMapping("/")
    UserDto getUser(@RequestParam("userName") String userName) {
        String accessToken = SecurityUtils.getAccessToken(httpServletRequest);
        if (StringUtils.isEmpty(userName)) {
            userName = SecurityUtils.getUsername(accessToken);
        }
        GetUserQuery getUserRequest = new GetUserQuery();
        getUserRequest.setUserName(userName);
        getUserRequest.setAccessToken(accessToken);
        return getUserUseCase.execute(getUserRequest);
    }
}
