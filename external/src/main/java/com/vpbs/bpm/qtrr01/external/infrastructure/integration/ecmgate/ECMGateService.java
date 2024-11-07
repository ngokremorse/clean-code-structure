package com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate;

import com.vpbs.bpm.qtrr01.external.config.FeignClientConfig;
import com.vpbs.bpm.qtrr01.external.infrastructure.integration.ecmgate.dto.*;
import feign.Headers;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Call sang api của ECM GATE
 */
@FeignClient(value = "ecmGateService", url = "${services.ecmGate.uri}", configuration = FeignClientConfig.class)
public interface ECMGateService {

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/folder/exists")
    void checkExistsFolder(@RequestBody CheckFolderExistsRequest checkFolderExistsRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/folder")
    void createFolder(@RequestBody CreateFolderRequest createFolderRequest);

    @Headers("Content-Type: multipart/form-data")
    @RequestMapping(method = RequestMethod.POST, value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart(value = "file") Resource file, @RequestPart(value = "data") UploadFileRequest uploadFileRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/file/{ecmId}/metadata")
    void updateMetadata(@PathVariable("ecmId") String ecmId, @RequestBody UpdateMetadataRequest updateMetadataRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/document/{ecmId}/permissions")
    void updatePermissionsFile(@RequestBody UpdatePermissionsRequest updatePermissionsRequest, @PathVariable("ecmId") String ecmId);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.DELETE, value = "/document/{ecmId}/permissions")
    void deletePermissionsFile(@RequestBody DeletePermissionsRequest deletePermissionsRequest, @PathVariable("ecmId") String ecmId);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/document/permissions")
    void updatePermissionDocuments(@RequestBody List<UpdatePermissionDocumentsRequest> updatePermissionDocumentsRequest);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.GET, value = "/file/{ecmId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    InputStreamResource getFile(@PathVariable("ecmId") String ecmId);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.DELETE, value = "/documents")
    void deleteFilesByQuery(@Valid @RequestBody DeleteFilesByQuery deleteFilesByQuery);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/file/documents")
    void deleteFiles(@Valid @RequestBody String[] ecmIds);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "/documents/property")
    List<EcmDocument> searchDocument(@Valid @RequestBody SearchDocumentByQuery searchDocumentByQuery);

}