package com.vpbs.bpm.qtrr01.external.infrastructure.integration.gendoc;
import com.vpbs.bpm.qtrr01.external.config.FeignClientConfig;
import com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto.ConvertEcmFileToPdfRequest;
import com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto.DocGenReq;
import com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto.GenerateDocByTemplateResponse;
import com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto.MergeFilesRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@FeignClient(value = "docgen", url = "${services.gendocGate.uri}", configuration = FeignClientConfig.class)
public interface GenDocGateService {
    @Headers("Content-Type: application/json")
    @PostMapping(value = "/generateDocument/byTemplate")
    GenerateDocByTemplateResponse generateDocByTemplate(@RequestBody DocGenReq docGenReq);

    @Headers("Content-Type: application/json")
    @PostMapping(value = "/documentTool/mergeFiles")
    Map<String, Object> mergeFiles(@RequestBody MergeFilesRequest mergeFilesReq);

    @Headers({"Content-Type: multipart/form-data"})
    @PostMapping(value = "/fileConvert/file2Pdf", consumes = {"multipart/form-data"})
    Resource convertFileToPDF(@RequestPart List<MultipartFile> files, @RequestPart String fileType);

    @Headers("Content-Type: application/json")
    @PostMapping(value = "/fileConvert/ecmFile2Pdf")
    Map<String, Object> convertEcmFileToPDF(@RequestBody ConvertEcmFileToPdfRequest convertEcmFileToPdfRequest);
}
