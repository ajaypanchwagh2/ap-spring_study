package com.example.Crud;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    @CacheEvict(value = {"persons", "allPersons"}, allEntries = true)
    public Person create(Person p) {
        return repo.save(p); // cache evicted to ensure fresh fetch
    }

    @Cacheable(value = "persons", key = "#id")
    public Person getOrThrow(Long id) {
        if (id == null || id <= 0)
            throw new InvalidInputException("Invalid id: " + id);
        return repo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id " + id));
    }

    @CacheEvict(value = {"persons", "allPersons"}, key = "#id", allEntries = true)
    public Person update(Long id, Person in) {
        Person p = getOrThrow(id);
        p.setName(in.getName());
        p.setAge(in.getAge());
        p.setEmail(in.getEmail());
        return repo.save(p);
    }

    @CacheEvict(value = {"persons", "allPersons"}, key = "#id", allEntries = true)
    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new PersonNotFoundException("Person not found with id " + id);
        repo.deleteById(id);
    }

    @Cacheable("allPersons")
    public List<Person> list() {
        return repo.findAll();
    }

    // No caching needed for now, since this is placeholder
    public List<Person> searchByName(String name) {
        return List.of(); // implement real query later
    }
}
