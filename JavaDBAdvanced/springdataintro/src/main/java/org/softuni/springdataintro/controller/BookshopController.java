package org.softuni.springdataintro.controller;

import org.softuni.springdataintro.service.AuthorService;
import org.softuni.springdataintro.service.BookService;
import org.softuni.springdataintro.repositories.CategoryRepository;
import org.softuni.springdataintro.domain.Author;
import org.softuni.springdataintro.domain.Book;
import org.softuni.springdataintro.domain.Category;
import org.softuni.springdataintro.domain.enums.AgeRestriction;
import org.softuni.springdataintro.domain.enums.EditionType;
import org.softuni.springdataintro.repositories.AuthorRepository;
import org.softuni.springdataintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class BookshopController implements CommandLineRunner {
    private static final String FILES_PATH = "C:\\Users\\admin\\Documents\\GitHub\\softunirepo\\JavaDBAdvanced\\springdataintro\\src\\main\\resources\\";

    private AuthorService authorService;

    private CategoryService categoryService;

    private BookService bookService;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.bookService.getAllBooksAfter2000();
    }

    private void seedDatabase() throws IOException, ParseException {
        this.categoryService.seedCategory();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }

}
