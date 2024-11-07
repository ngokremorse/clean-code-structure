package com.vpbs.bpm.qtrr01.core.usecase;


import com.vpbs.bpm.qtrr01.core.domain.UserDto;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.GetUserQuery;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserUseCase implements IUseCase<GetUserQuery, UserDto> {
    private final BpmService bpmService;

    @Override
    public UserDto execute(GetUserQuery bpmGetUserRequest) {
        return bpmService.getUser(bpmGetUserRequest.getAccessToken(), bpmGetUserRequest.getUserName());
    }
}
