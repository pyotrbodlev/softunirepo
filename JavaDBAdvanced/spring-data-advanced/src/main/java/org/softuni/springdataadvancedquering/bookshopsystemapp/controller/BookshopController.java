package org.softuni.springdataadvancedquering.bookshopsystemapp.controller;

import org.softuni.springdataadvancedquering.bookshopsystemapp.service.AuthorService;
import org.softuni.springdataadvancedquering.bookshopsystemapp.service.BookService;
import org.softuni.springdataadvancedquering.bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
    private final Scanner scanner;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService, Scanner scanner) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... strings) throws Exception {
        String title = scanner.nextLine();
        this.bookService.getAllBooksByTitle(title).forEach(System.out::println);
    }
}
