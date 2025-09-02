package com.example.Crud;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService svc;
    public PersonController(PersonService svc) { this.svc = svc; }

    // 201 Created + Location header
    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        Person saved = svc.create(person);
        return ResponseEntity
                .created(URI.create("/persons/" + saved.getId()))
                .body(saved); // 201
    }

    // 200 OK or 404 via exception handler
    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getOrThrow(id)); // 200
    }

    // 200 OK on update
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        return ResponseEntity.ok(svc.update(id, person)); // 200
    }

    // 204 No Content on delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    // 200 OK list
    @GetMapping
    public ResponseEntity<List<Person>> list() {
        return ResponseEntity.ok(svc.list()); // 200
    }

    // Optional: example of manual 400 for query param validation

    @GetMapping("/search")
    public ResponseEntity<List<Person>> search(@RequestParam(required = false) String name) {
        if (name == null || name.isBlank()) throw new InvalidInputException("name is required");
        return ResponseEntity.ok(svc.searchByName(name));  // ✅ NEW: Call service
    }

}
