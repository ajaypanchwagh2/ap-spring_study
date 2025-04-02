package com.example.Demo;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ConsoleItemWriter implements ItemWriter<Person> {

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        List<? extends Person> items = chunk.getItems();
        System.out.println("Being prited");
        items.forEach(System.out::println);
    }
}
