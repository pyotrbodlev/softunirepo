package org.softuni.springdataintro.repositories;

import org.softuni.springdataintro.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
