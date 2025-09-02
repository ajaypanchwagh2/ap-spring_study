package com.example.Crud;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "person")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull @Min(value = 0, message = "Age must be >= 0")
    private Integer age;

    @Email(message = "Invalid email format")
    private String email;

    // getters/setters/ctors...



// Constructors
    public Person() {
    }

    public Person(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public void setId(Long id) { // <-- Required for test
        this.id = id;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
