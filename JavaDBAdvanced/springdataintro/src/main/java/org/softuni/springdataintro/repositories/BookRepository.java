package org.softuni.springdataintro.repositories;

import org.softuni.springdataintro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    //List<Book> findAllByReleaseDate_YearAfter(int year);
    List<Book> findAllByReleaseDateAfter(Date date);
}
