package com.vpbs.bpm.qtrr01.external.api;


import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;
import com.vpbs.bpm.qtrr01.core.usecase.RemindTaskUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.ShareDocumentUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.ShareDocumentCommand;
import com.vpbs.bpm.qtrr01.external.api.dto.ShareDocumentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/api/tasklist")
public class TaskListRest {
    private final RemindTaskUseCase remindTaskUseCase;
    private final ShareDocumentUseCase shareDocumentUseCase;

    @PostMapping("/remind/{processId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remindTask(@PathVariable("processId") String processId) throws ApiException {
        remindTaskUseCase.execute(processId);
    }

    @PostMapping("/share/document")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void shareDocument(@Valid @RequestBody ShareDocumentRequest shareDocumentRequest) {
        ShareDocumentCommand shareDocumentCommand = new ShareDocumentCommand();
        BeanUtils.copyProperties(shareDocumentRequest, shareDocumentCommand);
        shareDocumentUseCase.execute(shareDocumentCommand);
    }

    @PostMapping("/task/recovery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void taskRecovery(@Valid @RequestBody ShareDocumentRequest shareDocumentRequest) {
        ShareDocumentCommand shareDocumentCommand = new ShareDocumentCommand();
        BeanUtils.copyProperties(shareDocumentRequest, shareDocumentCommand);
        shareDocumentUseCase.execute(shareDocumentCommand);
    }

}
