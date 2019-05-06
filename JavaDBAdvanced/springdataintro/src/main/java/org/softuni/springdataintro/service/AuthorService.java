package org.softuni.springdataintro.service;

import org.softuni.springdataintro.domain.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    List<Author> getAuthorsWithBookBefore();

    List<Author> getAuthorsOrderdByBooksCount();
}
