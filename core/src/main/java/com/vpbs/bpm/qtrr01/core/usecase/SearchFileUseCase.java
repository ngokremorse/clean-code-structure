package com.vpbs.bpm.qtrr01.core.usecase;


import com.vpbs.bpm.qtrr01.core.enums.EcmPropertiesEnum;
import com.vpbs.bpm.qtrr01.core.usecase.base.IUseCase;
import com.vpbs.bpm.qtrr01.core.usecase.base.UseCase;
import com.vpbs.bpm.qtrr01.core.usecase.dto.DocumentCriteria;
import com.vpbs.bpm.qtrr01.core.usecase.dto.EcmDocumentDto;
import com.vpbs.bpm.qtrr01.core.usecase.dto.SearchDocumentQuery;
import com.vpbs.bpm.qtrr01.core.usecase.service.EcmService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SearchFileUseCase implements IUseCase<SearchDocumentQuery, List<EcmDocumentDto>> {
    private final EcmService ecmService;

    @Override
    public List<EcmDocumentDto> execute(SearchDocumentQuery request) {

        // Required
        List<DocumentCriteria> criteria = new ArrayList<>();

        // process instance
        DocumentCriteria processIdCri = new DocumentCriteria();
        processIdCri.setPropertyName(EcmPropertiesEnum.ProcessInstanceID.name());
        processIdCri.setPropertyValue(request.getProcessId());
        criteria.add(processIdCri);

        // case id
        if (StringUtils.isEmpty(request.getCaseId())) {
            DocumentCriteria caseIdCri = new DocumentCriteria();
            caseIdCri.setPropertyName(EcmPropertiesEnum.caseId.name());
            caseIdCri.setPropertyValue(request.getCaseId());
            criteria.add(caseIdCri);
        }

        // category
        if (request.getCategory() != null) {
            DocumentCriteria categoryCri = new DocumentCriteria();
            categoryCri.setPropertyName(EcmPropertiesEnum.DocumentCategory.name());
            categoryCri.setPropertyValue(request.getCategory().name());
            criteria.add(categoryCri);
        }

        // properties attribute
        List<String> propertiesAttr = request.getPropertyAttributes().stream().map(Enum::name).toList();

        return ecmService.searchDocument(criteria, propertiesAttr);
    }
}
