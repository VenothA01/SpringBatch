package com.dailycoder.batchJob;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableBatchProcessing
public class BatchJobApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step packageItemStep() {
		return stepBuilderFactory.get("packageItemStep")
				.tasklet((stepContribution, chunkContext) -> {
					System.out.println("Hello World!");
					return RepeatStatus.FINISHED;
				}).build();
	}

	@Bean
	public org.springframework.batch.core.Job job() {
		return jobBuilderFactory.get("job")
				.start(packageItemStep())
				.build();
	}




	public static void main(String[] args) {
		SpringApplication.run(BatchJobApplication.class, args);
	}

}
