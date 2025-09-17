package com.example.Crud;

import io.micrometer.tracing.Tracer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persons")
@Tag(name = "Person Controller", description = "CRUD operations and search for Person entities with pagination")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;
    private final Tracer tracer;

    public PersonController(PersonService personService, Tracer tracer) {
        this.personService = personService;
        this.tracer = tracer;
    }

    @Operation(summary = "Create a new person (ADMIN only)")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        log.info("Creating person: {}, traceId={}", person, currentTraceId());
        Person saved = personService.create(person);
        return ResponseEntity.created(URI.create("/persons/" + saved.getId()))
                .header("X-Trace-Id", currentTraceId())
                .body(saved);
    }

    @Operation(summary = "Get a person by ID (ADMIN, VIEWER)")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VIEWER')")
    public ResponseEntity<Person> get(
            @Parameter(description = "ID of the person to fetch") @PathVariable Long id) {
        log.info("Fetching person with id: {}, traceId={}", id, currentTraceId());
        Person person = personService.getOrThrow(id);
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(person);
    }

    @Operation(summary = "Update a person by ID (ADMIN only)")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Person> update(
            @Parameter(description = "ID of the person to update") @PathVariable Long id,
            @Valid @RequestBody Person person) {
        log.info("Updating person with id: {}, traceId={}", id, currentTraceId());
        Person updated = personService.update(id, person);
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(updated);
    }

    @Operation(summary = "Delete a person by ID (ADMIN only)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the person to delete") @PathVariable Long id) {
        log.info("Deleting person with id: {}, traceId={}", id, currentTraceId());
        personService.delete(id);
        return ResponseEntity.noContent()
                .header("X-Trace-Id", currentTraceId())
                .build();
    }

    @Operation(summary = "List all persons with pagination (ADMIN, VIEWER)")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'VIEWER')")
    public ResponseEntity<Page<Person>> list(Pageable pageable) {
        log.info("Fetching persons with pagination, traceId={}", currentTraceId());
        Page<Person> result = personService.list(pageable);
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(result);
    }

    @Operation(summary = "Search persons by name with pagination (ADMIN, VIEWER)")
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'VIEWER')")
    public ResponseEntity<Page<Person>> search(
            @Parameter(description = "Name to search") @RequestParam(required = false) String name,
            Pageable pageable) {
        if (name == null || name.isBlank()) {
            log.warn("Search called without name parameter, traceId={}", currentTraceId());
            throw new InvalidInputException("name is required");
        }
        log.info("Searching persons by name: {}, traceId={}", name, currentTraceId());
        Page<Person> result = personService.searchByName(name, pageable);
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(result);
    }

    @Operation(summary = "Healthcheck - public endpoint")
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

    @Operation(summary = "Find persons older than given age (ADMIN, VIEWER)")
    @GetMapping("/older-than")
    @PreAuthorize("hasAnyRole('ADMIN', 'VIEWER')")
    public ResponseEntity<Page<Person>> findOlderThan(
            @RequestParam int age,
            Pageable pageable) {
        log.info("Searching persons older than {}, traceId={}", age, currentTraceId());
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(personService.findOlderThan(age, pageable));
    }


    @Operation(summary = "Find persons younger than given age (ADMIN, VIEWER)")
    @GetMapping("/younger-than")
    @PreAuthorize("hasAnyRole('ADMIN', 'VIEWER')")
    public ResponseEntity<Page<Person>> findyoungThan(
            @RequestParam int age,
            Pageable pageable) {
        log.info("Searching persons older than {}, traceId={}", age, currentTraceId());
        return ResponseEntity.ok()
                .header("X-Trace-Id", currentTraceId())
                .body(personService.findYoungThan(age, pageable));
    }


}
