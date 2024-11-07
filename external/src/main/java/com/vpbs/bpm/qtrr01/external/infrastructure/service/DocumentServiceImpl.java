package com.vpbs.bpm.qtrr01.external.infrastructure.service;

import com.vpbs.bpm.qtrr01.core.usecase.dto.MergeEcmFileToPdfCommand;
import com.vpbs.bpm.qtrr01.core.domain.MergeFileDto;
import com.vpbs.bpm.qtrr01.core.usecase.service.DocumentService;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.esign.dto.CustomMultipartFile;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.gendoc.GenDocGateService;
import com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto.ConvertEcmFileToPdfRequest;
import com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto.MergeFilesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final GenDocGateService genDocGateService;

    @Override
    public MultipartFile convertFilePdf(MultipartFile file, String fileName) throws IOException {
        Resource resource = genDocGateService.convertFileToPDF(Collections.singletonList(file), getEsignWorkerFileType(fileName));
        return new CustomMultipartFile(resource.getContentAsByteArray(), fileName);
    }

    @Override
    public Map<String, Object> mergeEcmFileToPdf(MergeEcmFileToPdfCommand mergeEcmFileToPdfCommand) {
        ConvertEcmFileToPdfRequest convertEcmFileToPdfRequest = new ConvertEcmFileToPdfRequest();
        BeanUtils.copyProperties(mergeEcmFileToPdfCommand, convertEcmFileToPdfRequest);
        return genDocGateService.convertEcmFileToPDF(convertEcmFileToPdfRequest);
    }

    @Override
    public Map<String, Object> mergeFiles(MergeFileDto mergeFilesProps) {
        MergeFilesRequest mergeFilesRequest = new MergeFilesRequest();
        BeanUtils.copyProperties(mergeFilesProps, mergeFilesRequest);
        return genDocGateService.mergeFiles(mergeFilesRequest);
    }

    private String getEsignWorkerFileType(String fileName) {
        String extensionFile = fileName.toLowerCase().substring(fileName.lastIndexOf(".") + 1);
        if ("jpeg,jpg,png,gif,bmp,wbmp".contains(extensionFile)) {
            return "IMAGE";
        } else if ("doc,docx,xls,xlsx,ppt,pptx".contains(extensionFile)) {
            return "OFFICE";
        }
        return null;
    }
}
