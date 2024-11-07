package com.vpbs.bpm.qtrr01.external.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "case_id_latest")
@Data
public class CaseIdLatestEntity {
    @Id
    private Long id;
    private String label;
    private int value;
}
