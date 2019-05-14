package org.softuni.springdataadvancedquering.bookshopsystemapp.repository;

import org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAllByFirstNameEndingWith(String pattern);

    List<Author> findAllByLastNameEndingWith(String pattern);

    List<Author> findAllByLastNameStartingWith(String pattern);

}
