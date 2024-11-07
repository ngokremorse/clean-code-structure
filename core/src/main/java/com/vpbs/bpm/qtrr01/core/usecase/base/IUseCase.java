package com.vpbs.bpm.qtrr01.core.usecase.base;

import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;

public interface IUseCase<I, O> {

    O execute(I request) throws ApiException;
}
