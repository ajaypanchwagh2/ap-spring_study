package com.example.Crud;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    @Transactional
    @CacheEvict(value = {"persons", "allPersons"}, allEntries = true)
    public Person create(Person p) {
        return repo.save(p); // cache evicted to ensure fresh fetch
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "persons", key = "#id")
    public Person getOrThrow(Long id) {
        if (id == null || id <= 0)
            throw new InvalidInputException("Invalid id: " + id);
        return repo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id " + id));
    }

    @Transactional
    @CacheEvict(value = {"persons", "allPersons"}, key = "#id", allEntries = true)
    public Person update(Long id, Person in) {
        Person p = getOrThrow(id);
        p.setName(in.getName());
        p.setAge(in.getAge());
        p.setEmail(in.getEmail());
        return repo.save(p);
    }

    @Transactional
    @CacheEvict(value = {"persons", "allPersons"}, key = "#id", allEntries = true)
    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new PersonNotFoundException("Person not found with id " + id);
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Cacheable("allPersons")
    public Page<Person> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Person> searchByName(String name, Pageable pageable) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Name must not be blank");
        }
        return repo.findByNameContainingIgnoreCase(name, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Person> findOlderThan(int age, Pageable pageable) {
        if (age < 0) throw new InvalidInputException("Age must be positive");
        return repo.findByAgeGreaterThanNative(age, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Person> findYoungThan(int age, Pageable pageable) {
        if (age < 0) throw new InvalidInputException("Age must be positive");
        return repo.findByAgelessThanNative(age, pageable);
    }
}
