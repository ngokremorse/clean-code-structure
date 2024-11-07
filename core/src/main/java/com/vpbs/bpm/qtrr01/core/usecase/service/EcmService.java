package com.vpbs.bpm.qtrr01.core.usecase.service;

import com.vpbs.bpm.qtrr01.core.usecase.dto.DocumentCriteria;
import com.vpbs.bpm.qtrr01.core.usecase.dto.EcmDocumentDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.EcmProperties;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EcmService {
    String uploadDocument(MultipartFile file, List<String> additionalUsers, EcmProperties properties);
    void deleteDocuments(List<String> fileIds);
    void createFolder(String folderName, String parentFolderPath);
    List<EcmDocumentDto> searchDocument(List<DocumentCriteria> criteria, List<String> propertiesAttr);
    void updatePermissionDocumentsByProcessId(String processId, String userName);
    String baseFolder();
    String className();
    String url();
    String urlViewFile();
}