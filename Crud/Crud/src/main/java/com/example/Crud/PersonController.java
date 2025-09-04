package com.example.Crud;

import io.micrometer.tracing.Tracer;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;
    private final Tracer tracer;

    public PersonController(PersonService personService, Tracer tracer) {
        this.personService = personService;
        this.tracer = tracer;
    }

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        log.info("Creating person: {}, traceId={}", person, currentTraceId());
        Person saved = personService.create(person);
        return ResponseEntity.created(URI.create("/persons/" + saved.getId()))
                .header("X-Trace-Id", currentTraceId())
                .body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable Long id) {
        log.info("Fetching person with id: {}, traceId={}", id, currentTraceId());
        Person person = personService.getOrThrow(id);
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        log.info("Updating person with id: {}, traceId={}", id, currentTraceId());
        Person updated = personService.update(id, person);
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting person with id: {}, traceId={}", id, currentTraceId());
        personService.delete(id);
        return ResponseEntity.noContent()
                .header("X-Trace-Id", currentTraceId())
                .build();
    }

    @GetMapping
    public ResponseEntity<List<Person>> list() {
        log.info("Fetching all persons, traceId={}", currentTraceId());
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(personService.list());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Person>> search(@RequestParam(required = false) String name) {
        if (name == null || name.isBlank()) {
            log.warn("Search called without name parameter, traceId={}", currentTraceId());
            throw new InvalidInputException("name is required");
        }
        log.info("Searching persons by name: {}, traceId={}", name, currentTraceId());
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(personService.searchByName(name));
    }

    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthcheck() {
        log.info("Healthcheck endpoint called, traceId={}", currentTraceId());
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body("ok");
    }

    private String currentTraceId() {
        return tracer.currentSpan() != null ? tracer.currentSpan().context().traceId() : "N/A";
    }
}
