package com.vpbs.bpm.qtrr01.external.infrastructure.taskhandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpbs.bpm.qtrr01.core.enums.EcmDocumentCategoryEnum;
import com.vpbs.bpm.qtrr01.core.enums.ProcessKeyEnum;
import com.vpbs.bpm.qtrr01.core.usecase.SearchFileUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.MergeEcmFileToPdfCommand;
import com.vpbs.bpm.qtrr01.core.usecase.dto.EcmDocumentDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.SearchDocumentQuery;
import com.vpbs.bpm.qtrr01.core.usecase.service.DocumentService;
import com.vpbs.bpm.qtrr01.external.infrastructure.utils.ExternalTaskUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@ExternalTaskSubscription(topicName = "mergeEcmFileToPdf", processDefinitionKey = "QTRR_01")
@RequiredArgsConstructor
public class MergeEcmFileToPdfHandler implements ExternalTaskHandler {
    private final DocumentService documentService;
    private final SearchFileUseCase searchFileUseCase;
    private final ObjectMapper objectMapper;
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        // Lấy danh sách file cần merge
        SearchDocumentQuery searchDocumentQuery = new SearchDocumentQuery();
        searchDocumentQuery.setProcessId(externalTask.getProcessInstanceId());
        searchDocumentQuery.setCaseId(externalTask.getVariable("caseId"));
        searchDocumentQuery.setCategory(EcmDocumentCategoryEnum.signature);

        List<EcmDocumentDto> ecmDocuments = searchFileUseCase.execute(searchDocumentQuery);
        List<String> fileIds = ecmDocuments.stream().map(EcmDocumentDto::getId).toList();

        Object viewersObject = externalTask.getVariable("viewers");
        String caseId = externalTask.getVariable("caseId");
        ArrayList<String> viewers = viewersObject != null
                ? objectMapper.convertValue(viewersObject, new TypeReference<>() {})
                : new ArrayList<>();

        // merge file trên ecm
        MergeEcmFileToPdfCommand mergeEcmFileToPdfCommand = new MergeEcmFileToPdfCommand();
        mergeEcmFileToPdfCommand.setFileType("OFFICE");
        mergeEcmFileToPdfCommand.setFileViewers(viewers);
        mergeEcmFileToPdfCommand.setCaseId(caseId);
        mergeEcmFileToPdfCommand.setGroup(ProcessKeyEnum.QTRR_01.name());
        mergeEcmFileToPdfCommand.setOutputFileName(caseId + "_merge.pdf");
        mergeEcmFileToPdfCommand.setFileIds(fileIds);
        try {
            Map<String, Object> convertEcmFileToPdfResponse = documentService.mergeEcmFileToPdf(mergeEcmFileToPdfCommand);
            if (!"200".equals(convertEcmFileToPdfResponse.get("code"))) {
                throw new RuntimeException("Convert ecm file sang pdf không thành công | " + convertEcmFileToPdfResponse.get("message"));
            }
            VariableMap variables = Variables.createVariables();
            variables.put("ecmFileMerge", convertEcmFileToPdfResponse.get("data"));
            externalTaskService.complete(externalTask, variables);
        } catch (Exception e) {
            log.error(e);
            externalTaskService.handleFailure(externalTask,
                    externalTask.getWorkerId(),
                    e.getLocalizedMessage(),
                    ExternalTaskUtils.getRetries(externalTask, 3),
                    60000);
        }

    }
}
