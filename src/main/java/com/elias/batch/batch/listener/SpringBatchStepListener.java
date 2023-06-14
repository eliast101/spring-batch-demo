package com.elias.batch.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class SpringBatchStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("SPStepListener - CALLED BEFORE STEP.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("SPStepListener - CALLED AFTER STEP.");
        return ExitStatus.COMPLETED;
    }
}
