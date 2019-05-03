package org.softuni.springdataintro.repositories;

import org.softuni.springdataintro.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
