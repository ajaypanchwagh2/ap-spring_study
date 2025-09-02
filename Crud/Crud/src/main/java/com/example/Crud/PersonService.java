package com.example.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // Create a new person (POST)
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    // Retrieve a person by ID (GET)
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    // Update a person (PUT)
    public Person updatePerson(Long id, Person updatedPerson) {
        return personRepository.findById(id)
                .map(existingPerson -> {
                    existingPerson.setName(updatedPerson.getName());
                    existingPerson.setAge(updatedPerson.getAge());
                    existingPerson.setEmail(updatedPerson.getEmail());
                    return personRepository.save(existingPerson);
                })
                .orElseThrow(() -> new RuntimeException("Person not found with id " + id));
    }

    // Delete a person (DELETE)
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Person not found with id " + id);
        }
        personRepository.deleteById(id);
    }

    // Retrieve all persons (GET)
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(Long id )
    {
        return personRepository.findById(id);
    }
}
