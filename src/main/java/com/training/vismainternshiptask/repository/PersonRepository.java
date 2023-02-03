package com.training.vismainternshiptask.repository;

import com.training.vismainternshiptask.model.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


 Optional<Person> findByNameIgnoreCase(String name);

}
