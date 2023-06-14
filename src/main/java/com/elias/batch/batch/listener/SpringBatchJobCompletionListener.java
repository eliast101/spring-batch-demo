package com.elias.batch.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Slf4j
public class SpringBatchJobCompletionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("SpringBatchJobCompletionListener - BEFORE BATCH JOB STARTS");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("SpringBatchJobCompletionListener - BATCH JOB COMPLETED SUCCESSFULLY");
        }else if(jobExecution.getStatus() == BatchStatus.FAILED){
            log.info("SpringBatchJobCompletionListener - BATCH JOB FAILED");
        }
    }

}
