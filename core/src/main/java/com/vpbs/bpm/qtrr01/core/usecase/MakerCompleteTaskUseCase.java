package com.vpbs.bpm.qtrr01.core.usecase;


import com.vpbs.bpm.qtrr01.core.domain.DocumentFile;
import com.vpbs.bpm.qtrr01.core.domain.ProcessData;
import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;
import com.vpbs.bpm.qtrr01.core.enums.EcmDocumentCategoryEnum;
import com.vpbs.bpm.qtrr01.core.enums.EcmPropertiesEnum;
import com.vpbs.bpm.qtrr01.core.enums.MakerActionEnum;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.DocumentCriteria;
import com.vpbs.bpm.qtrr01.core.usecase.dto.EcmDocumentDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.MakerCompleteTaskCommand;
import com.vpbs.bpm.qtrr01.core.usecase.service.BpmService;
import com.vpbs.bpm.qtrr01.core.usecase.service.EcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class MakerCompleteTaskUseCase implements IUseCase<MakerCompleteTaskCommand, String> {
    private final BpmService bpmService;
    private final EcmService ecmService;

    @Override
    @Transactional
    public String execute(MakerCompleteTaskCommand request) throws ApiException {
        // validate data and user

        //build process data
        ProcessData processData = new ProcessData();
        BeanUtils.copyProperties(request, processData);
        processData.setMakerAction(MakerActionEnum.complete);

        // Build hash map
        Map<String, Object> camundaProcessData = null;
        try {
            camundaProcessData = processData.toMap();
        } catch (IllegalAccessException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid data", e.getMessage());
        }

        // sync list document names to camunda
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPropertyName(EcmPropertiesEnum.ProcessInstanceID.name());
        documentCriteria.setPropertyValue(request.getProcessInstanceId());
        List<EcmDocumentDto> ecmDocumentDtos = ecmService.searchDocument(List.of(documentCriteria), new ArrayList<>());
        // Build ra các danh sách file

        // attachment, signature
        if (ecmDocumentDtos.size() > 0) {
            List<DocumentFile> attachFiles = new ArrayList<>();
            List<DocumentFile> signatureFiles = new ArrayList<>();

            for (EcmDocumentDto ecmFile : ecmDocumentDtos) {
                String categoryFile = ecmFile.getProperties().get("DocumentCategory").toString();
                if(EcmDocumentCategoryEnum.attach.name().equals(categoryFile)) {
                    attachFiles.add(DocumentFile
                            .builder()
                            .fileUrl(String.format(ecmService.urlViewFile(), ecmFile.getId()))
                            .fileName(ecmFile.getFileName())
                            .build());
                } else if(EcmDocumentCategoryEnum.signature.name().equals(categoryFile)) {
                    signatureFiles.add(DocumentFile
                            .builder()
                            .fileUrl(String.format(ecmService.urlViewFile(), ecmFile.getId()))
                            .fileName(ecmFile.getFileName())
                            .build());
                }
            }
            camundaProcessData.put("documentEcmAttachFiles", attachFiles);
            camundaProcessData.put("documentEcmSignatureFiles", signatureFiles);
        }
        // complete task
        bpmService.completeTask(request.getTaskId(), request.getAccessToken(), camundaProcessData);
        return null;
    }
}
