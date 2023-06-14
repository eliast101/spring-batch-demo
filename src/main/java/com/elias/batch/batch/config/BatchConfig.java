package com.elias.batch.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing(dataSourceRef = "secondaryDatasource", transactionManagerRef = "transactionManager")
public class BatchConfig {
}
