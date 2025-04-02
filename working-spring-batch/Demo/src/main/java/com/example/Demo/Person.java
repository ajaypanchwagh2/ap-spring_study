package com.example.Demo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
public class Person {
    @Id
    private int id;
    private String name;
    private String lastname;

    // Required for Spring Batch
    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastname;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setlstName(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name +  ", lastname= "+lastname+"]";
    }
}
