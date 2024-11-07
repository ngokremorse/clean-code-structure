package com.vpbs.bpm.qtrr01.external.api;

import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;
import com.vpbs.bpm.qtrr01.core.enums.ProcessKeyEnum;
import com.vpbs.bpm.qtrr01.core.usecase.StartProcessUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.SearchFileUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.StoreDataUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.StoreFileUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.*;
import com.vpbs.bpm.qtrr01.external.api.dto.FilePropertiesRequest;
import com.vpbs.bpm.qtrr01.external.api.dto.SearchDocumentRequest;
import com.vpbs.bpm.qtrr01.external.infrastructure.service.ISecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/api/process")
public class ProcessRest {
    private final StartProcessUseCase startProcessUseCase;
    private final StoreDataUseCase storeDataUseCase;
    private final StoreFileUseCase storeFileUseCase;
    private final SearchFileUseCase searchFileUseCase;
    private final ISecurityService securityService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public StartProcessResult startBondProcess(@Valid @RequestBody Map<String, Object> processData) {
        StartProcessCommand request = new StartProcessCommand();
        request.setProcessData(processData);
        request.setProcessKey(ProcessKeyEnum.QTRR_01.name().toLowerCase(Locale.ROOT));
        return startProcessUseCase.execute(request);
    }

    @RequestMapping(value = "/{processId}/data/store", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void storeBondData(@PathVariable("processId") String processId, @Valid @RequestBody Map<String, Object> processData) throws ApiException {
        securityService.checkAccessProcess(processId);

        StoreDataCommand request = new StoreDataCommand();
        request.setProcessInstanceId(processId);
        request.setProcessData(processData);
        storeDataUseCase.execute(request);
    }

    @RequestMapping(value = "{processId}/file/store", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public String storeFile(@PathVariable("processId") String processId,
                            @Valid @RequestPart("file") MultipartFile file,
                            @Valid @RequestPart("data") FilePropertiesRequest data) throws ApiException {
        securityService.checkAccessProcess(processId);

        StoreFileCommand storeFileCommand = new StoreFileCommand();
        BeanUtils.copyProperties(data, storeFileCommand);

        EcmProperties ecmProperties = EcmProperties
                .builder()
                .processInstanceId(processId)
                .category(data.getCategory().name())
                .build();

        storeFileCommand.setProperties(ecmProperties);
        storeFileCommand.setFile(file);

        return storeFileUseCase.execute(storeFileCommand);
    }

    @RequestMapping(value = "{processId}/file/search", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public List<EcmDocumentDto> searchFile(@PathVariable("processId") String processId,
                                           @Valid @RequestBody SearchDocumentRequest searchDocumentRequest) throws ApiException {
        securityService.checkAccessProcess(processId);

        SearchDocumentQuery ecmSearchDocumentRequest = new SearchDocumentQuery();
        ecmSearchDocumentRequest.setProcessId(processId);

        BeanUtils.copyProperties(searchDocumentRequest, ecmSearchDocumentRequest);
        return searchFileUseCase.execute(ecmSearchDocumentRequest);
    }
}
