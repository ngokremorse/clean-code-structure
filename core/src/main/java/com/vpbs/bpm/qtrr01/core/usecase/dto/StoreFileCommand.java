package com.vpbs.bpm.qtrr01.core.usecase.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class StoreFileCommand {

    private MultipartFile file;
    private String baseFolder;
    private String className;
    private List<String> additionalUsers;
    private EcmProperties properties;
}
