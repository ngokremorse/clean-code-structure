package com.vpbs.bpm.qtrr01.external.infrastructure.service;

import com.vpbs.bpm.qtrr01.core.usecase.service.CaseIdLatestService;
import com.vpbs.bpm.qtrr01.external.infrastructure.repository.CaseIdLatestRepository;
import com.vpbs.bpm.qtrr01.external.infrastructure.entity.CaseIdLatestEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class CaseIdLatestServiceImpl implements CaseIdLatestService {
    private final CaseIdLatestRepository caseIdLatestRepository;

    @Override
    public String generateCaseId() {
        caseIdLatestRepository.updateValueCaseIdLatest(1);
        CaseIdLatestEntity caseIdLatestEntity = caseIdLatestRepository.findById(1L).get();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return caseIdLatestEntity.getLabel() + year + StringUtils.leftPad(caseIdLatestEntity.getValue() + "", 8, '0');
    }
}
