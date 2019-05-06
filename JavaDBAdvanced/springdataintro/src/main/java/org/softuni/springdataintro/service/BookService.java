package org.softuni.springdataintro.service;

import java.io.IOException;
import java.text.ParseException;

public interface BookService {
    void seedBooks() throws IOException, ParseException;

    void getAllBooksAfter2000() throws ParseException;
}
