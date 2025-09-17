package com.example.Crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // No need to write any methods unless you want custom queries
    Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable);
    @Query(value = "SELECT * FROM person p WHERE p.age > :age", nativeQuery = true)
    Page<Person> findByAgeGreaterThanNative(int age, Pageable pageable);
    @Query(value = "SELECT * FROM person p WHERE p.age < :age", nativeQuery = true)
    Page<Person> findByAgelessThanNative(int age, Pageable pageable);


}



