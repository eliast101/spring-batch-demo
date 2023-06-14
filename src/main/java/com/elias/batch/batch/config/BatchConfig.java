package com.elias.batch.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.TransactionManager;

@Configuration
@EnableBatchProcessing(dataSourceRef = "secondaryDatasource", transactionManagerRef = "customTransactionManager")
public class BatchConfig {

    @Bean("customTransactionManager")
    public ResourcelessTransactionManager customTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    @Primary
    public TransactionManager transactionManager(TransactionManager transactionManager) {
        return transactionManager;
    }

}
