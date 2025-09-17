package com.example.Crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // ✅ KEEP THIS

@WebMvcTest(PersonController.class)
@Import(PersonControllerTest.MockPersonServiceConfig.class)  // Import our mock config
public class PersonControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Autowired private PersonService personService;  // Will be injected from our mock config

    @TestConfiguration
    static class MockPersonServiceConfig {
        @Bean
        @Primary
        public PersonService mockPersonService() {
            return Mockito.mock(PersonService.class);
        }
    }

    @Test
    void testCreatePerson() throws Exception {
        Person input = new Person();
        input.setName("Alice");
        input.setAge(30);
        input.setEmail("alice@example.com");

        Person saved = new Person();
        saved.setId(1L);
        saved.setName("Alice");
        saved.setAge(30);
        saved.setEmail("alice@example.com");

        Mockito.when(personService.create(any(Person.class))).thenReturn(saved);

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/persons/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));
    }
//    @Test
//    void testSearchPerson() throws Exception {
//        Person p = new Person();
//        p.setId(1L);
//        p.setName("Alice");
//        p.setAge(30);
//        p.setEmail("alice@example.com");
//
//        // ✅ Mock correct method (searchByName, not list)
//        Mockito.when(personService.searchByName("Alice")).thenReturn((Page<Person>) List.of(p));
//
//        mockMvc.perform(get("/persons/search")
//                        .param("name", "Alice")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("Alice"))
//                .andExpect(jsonPath("$[0].age").value(30))
//                .andExpect(jsonPath("$[0].email").value("alice@example.com"));
//    }




}
