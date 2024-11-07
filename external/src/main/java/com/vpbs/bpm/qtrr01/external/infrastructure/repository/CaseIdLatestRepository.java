package com.vpbs.bpm.qtrr01.external.infrastructure.repository;

import com.vpbs.bpm.qtrr01.external.infrastructure.entity.CaseIdLatestEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseIdLatestRepository extends CrudRepository<CaseIdLatestEntity, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update CaseIdLatestEntity c set c.value = c.value + 1 where c.id = :id")
    void updateValueCaseIdLatest(int id);
}
