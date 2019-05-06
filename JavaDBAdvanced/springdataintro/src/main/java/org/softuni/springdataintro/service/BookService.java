package org.softuni.springdataintro.service;

import org.softuni.springdataintro.domain.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException, ParseException;

    void getAllBooksAfter2000() throws ParseException;

    List<Book> getBookOfTheAuthor(String firstName, String lastName);
}
