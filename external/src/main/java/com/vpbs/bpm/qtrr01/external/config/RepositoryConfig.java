package com.vpbs.bpm.qtrr01.external.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = "com.vpbs.bpm.*")
@EnableJpaRepositories(basePackages = "com.vpbs.bpm.*")
@EnableTransactionManagement
public class RepositoryConfig {
}