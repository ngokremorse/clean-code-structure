package com.vpbs.bpm.qtrr01.core.usecase;


import com.vpbs.bpm.qtrr01.core.domain.UserDto;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllUserUseCase implements IUseCase<String, List<UserDto>> {
    private final BpmService bpmService;

    @Override
    public List<UserDto> execute(String accessToken) {
        return bpmService.getAllUser(accessToken);
    }
}
