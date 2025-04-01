package com.example.Demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job importUserJob;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ApplicationRunner runJob() {
		return args -> {
			jobLauncher.run(importUserJob,
					new JobParametersBuilder()
							.addLong("time", System.currentTimeMillis())
							.toJobParameters());
		};
	}
}
