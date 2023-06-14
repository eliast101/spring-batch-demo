package com.elias.batch.batch.job;

import com.elias.batch.batch.listener.SpringBatchJobCompletionListener;
import com.elias.batch.batch.listener.SpringBatchJobExecutionListener;
import com.elias.batch.batch.listener.SpringBatchStepListener;
import com.elias.batch.batch.step.StockInfoProcessor;
import com.elias.batch.model.StockInfo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfig {

    @Bean
    public Job processJob(JobRepository jobRepository, @Qualifier("stockPricesInfoStep") Step stockPricesInfoStep) {
        return new JobBuilder("stockpricesinfojob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(new SpringBatchJobExecutionListener())
                .flow(stockPricesInfoStep)
                .end()
                .build();
    }

    @Bean("stockPricesInfoStep")
    public Step stockPricesInfoStep(JobRepository jobRepository, /*@Qualifier("customTransactionManager")*/ PlatformTransactionManager transactionManager) {
        return new StepBuilder("stockPricesInfoStep", jobRepository)
                .listener(new SpringBatchStepListener())
                .<StockInfo, String>chunk(10, transactionManager)
                .reader(reader())
                .processor(stockInfoProcessor())
                .writer(writer())
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class)
                .build();
    }

    @Bean
    public FlatFileItemReader<StockInfo> reader() {
        return new FlatFileItemReaderBuilder<StockInfo>()
                .name("stockInfoItemReader")
                .resource(new ClassPathResource("csv/stockinfo.csv"))
                .delimited()
                .names(new String[] {"stockId", "stockName","stockPrice","yearlyHigh","yearlyLow","address","sector","market"})
                .targetType(StockInfo.class)
                .build();
    }

    @Bean
    public StockInfoProcessor stockInfoProcessor(){
        return new StockInfoProcessor();
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        return new FlatFileItemWriterBuilder<String>()
                .name("stockInfoItemWriter")
                .resource(new FileSystemResource(
                        "build/output.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new SpringBatchJobCompletionListener();
    }
}
