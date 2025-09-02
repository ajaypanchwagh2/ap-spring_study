package com.example.Crud;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repo;
    public PersonService(PersonRepository repo) { this.repo = repo; }

    public Person create(Person p) { return repo.save(p); }

    public Person getOrThrow(Long id) {
        if (id == null || id <= 0) throw new InvalidInputException("Invalid id: " + id); // → 400
        return repo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id " + id)); // → 404
    }

    public Person update(Long id, Person in) {
        Person p = getOrThrow(id);
        p.setName(in.getName());
        p.setAge(in.getAge());
        p.setEmail(in.getEmail());
        return repo.save(p); // → 200
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new PersonNotFoundException("Person not found with id " + id); // → 404
        repo.deleteById(id); // → 204
    }

    public List<Person> list() { return repo.findAll(); } // → 200
    public List<Person> searchByName(String name) {
        // Replace this with real repo call in future
        return List.of(); // dummy implementation
    }


}
