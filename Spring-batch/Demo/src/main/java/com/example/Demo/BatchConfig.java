package com.example.Demo;


import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("names.csv"))
                .delimited()
                .names("id", "name","lastname") // These are the column names
                .targetType(Person.class)
                .linesToSkip(1)      // ✅ Skip CSV header
                .build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

   /* @Bean
    public ConsoleItemWriter writer() {
        return new ConsoleItemWriter();
    }
*/

    @Bean
    public ItemWriter<Person> writer(PersonRepository personRepository) {
        return personRepository::saveAll;
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemWriter<Person> writer // ✅ Let Spring inject the writer bean
    ) {
        return new StepBuilder("step1", jobRepository)
                .<Person, Person>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer) // ✅ use injected writer
                .build();
    }

    // 👇 Add this method right here in BatchConfig

}
