package org.softuni.springdataintro.repositories;

import org.softuni.springdataintro.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findAllByFirstNameAndLastName(String firstName, String lastName);
}
