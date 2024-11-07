package com.vpbs.bpm.qtrr01.external.infrastructure.service;

import com.vpbs.bpm.qtrr01.core.enums.EcmPropertiesEnum;
import com.vpbs.bpm.qtrr01.core.usecase.dto.DocumentCriteria;
import com.vpbs.bpm.qtrr01.core.usecase.dto.EcmDocumentDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.EcmProperties;
import com.vpbs.bpm.qtrr01.core.usecase.service.EcmService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.ECMGateService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto.CreateFolderRequest;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto.SearchDocumentByQuery;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto.UpdatePermissionDocumentsRequest;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto.UploadFileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmcServiceImpl implements EcmService {
    private final ECMGateService ecmGateService;

    @Value("${services.ecmGate.uri}")
    private String emcUrl;

    @Value("${services.ecmGate.baseFolder}")
    private String baseFolder;

    @Value("${services.ecmGate.ecmClassName}")
    private String className;

    @Value("${services.ecmGate.viewFileUri}")
    private String urlViewFile;

    @Override
    public String uploadDocument(MultipartFile file, List<String> additionalUsers, EcmProperties properties) {
        UploadFileRequest uploadFileRequest = new UploadFileRequest();
        uploadFileRequest.setClassName(className);
        uploadFileRequest.setBaseFolder(baseFolder + "/" + properties.getCaseId());
        uploadFileRequest.setProperties(properties.toHashmap());
        uploadFileRequest.setAdditionalUsers(additionalUsers);
        try {
            LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("data", uploadFileRequest);
            map.add("file", file.getResource());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
            return new RestTemplate().postForObject(emcUrl + "/file", requestEntity, String.class);
        } catch (HttpStatusCodeException e) {
            log.error("File server return error[{}], {}", e.getStatusCode().value(), e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("Unexpected error while upload file", e);
        }
        return null;
    }

    @Override
    public void deleteDocuments(List<String> fileIds) {
        ecmGateService.deleteFiles(fileIds.toArray(new String[0]));
    }

    @Override
    public void createFolder(String folderName, String parentFolderPath) {
        CreateFolderRequest createFolderRequest = new CreateFolderRequest();
        createFolderRequest.setFolderName(folderName);
        createFolderRequest.setParentFolderPath(parentFolderPath);
        ecmGateService.createFolder(createFolderRequest);
    }

    @Override
    public List<EcmDocumentDto> searchDocument(List<DocumentCriteria> criteria, List<String> propertiesAttr) {
        SearchDocumentByQuery searchDocumentByQuery = new SearchDocumentByQuery();
        searchDocumentByQuery.setClassName(className);
        searchDocumentByQuery.setPropertyCriteria(criteria);
        searchDocumentByQuery.setPropertyResponse(propertiesAttr);

        return ecmGateService.searchDocument(searchDocumentByQuery).stream().map(item -> {
            EcmDocumentDto ecmDocumentDto = new EcmDocumentDto();
            BeanUtils.copyProperties(item, ecmDocumentDto);
            return ecmDocumentDto;
        }).toList();
    }

    @Override
    public void updatePermissionDocumentsByProcessId(String processId, String userName) {
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPropertyName(EcmPropertiesEnum.ProcessInstanceID.name());
        documentCriteria.setPropertyValue(processId);
        List<UpdatePermissionDocumentsRequest> updatePermissionDocumentsRequests = searchDocument(List.of(documentCriteria), new ArrayList<>()).stream()
                .map(item -> {
                    UpdatePermissionDocumentsRequest updatePermissionDocumentsRequest = new UpdatePermissionDocumentsRequest();
                    updatePermissionDocumentsRequest.setEcmId(item.getId());
                    updatePermissionDocumentsRequest.setAccountList(Collections.singletonList(userName));
                    return updatePermissionDocumentsRequest;
                }).toList();
        ecmGateService.updatePermissionDocuments(updatePermissionDocumentsRequests);
    }

    @Override
    public String baseFolder() {
        return baseFolder;
    }

    @Override
    public String className() {
        return className;
    }

    @Override
    public String url() {
        return emcUrl;
    }

    @Override
    public String urlViewFile() {
        return urlViewFile;
    }
}
