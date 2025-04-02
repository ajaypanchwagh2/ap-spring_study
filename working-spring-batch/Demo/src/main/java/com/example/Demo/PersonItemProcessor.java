package com.example.Demo;


import org.springframework.batch.item.ItemProcessor;

import java.awt.desktop.SystemEventListener;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        String upperName = person.getName().toUpperCase();
        person.setName(upperName);
        person.setlstName(person.getLastName().toUpperCase());
        System.out.println("Here in proceesor");
        return person;
    }
}
