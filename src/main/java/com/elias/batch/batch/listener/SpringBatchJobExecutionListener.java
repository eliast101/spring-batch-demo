package com.elias.batch.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class SpringBatchJobExecutionListener implements JobExecutionListener {

    public void beforeJob(JobExecution jobExecution) {
        log.info("BEFORE BATCH JOB STARTS");
    }

    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("BATCH JOB COMPLETED SUCCESSFULLY");
        }else if(jobExecution.getStatus() == BatchStatus.FAILED){
            log.info("BATCH JOB FAILED");
        }
    }
}
