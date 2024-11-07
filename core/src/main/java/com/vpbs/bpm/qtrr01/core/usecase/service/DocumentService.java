package com.vpbs.bpm.qtrr01.core.usecase.service;

import com.vpbs.bpm.qtrr01.core.domain.MergeFileDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface DocumentService {

    MultipartFile convertFilePdf(MultipartFile file, String fileName) throws IOException;
    Map<String, Object> mergeEcmFileToPdf(MergeEcmFileToPdfCommand mergeEcmFileToPdfCommand);
    Map<String, Object> mergeFiles(MergeFileDto mergeFilesProps);
}