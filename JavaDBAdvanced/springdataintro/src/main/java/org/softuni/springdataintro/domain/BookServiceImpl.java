package org.softuni.springdataintro.domain;

import org.softuni.springdataintro.entities.Book;
import org.softuni.springdataintro.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public void save(Book book) {
        this.repository.save(book);
    }
}
