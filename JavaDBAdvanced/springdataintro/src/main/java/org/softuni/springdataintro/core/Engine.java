package org.softuni.springdataintro.core;

import org.softuni.springdataintro.domain.BookService;
import org.softuni.springdataintro.domain.BookServiceImpl;
import org.softuni.springdataintro.domain.CategoryRepository;
import org.softuni.springdataintro.entities.Author;
import org.softuni.springdataintro.entities.Book;
import org.softuni.springdataintro.entities.Category;
import org.softuni.springdataintro.entities.enums.AgeRestriction;
import org.softuni.springdataintro.entities.enums.EditionType;
import org.softuni.springdataintro.repositories.AuthorRepository;
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
public class Engine implements CommandLineRunner {
    private static final String FILES_PATH = "C:\\Users\\disel\\IdeaProjects\\springdataintro\\src\\main\\resources\\";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookService bookService;


    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();
    }

    public void seedDatabase() throws IOException, ParseException {
        Random random = new Random();

        BufferedReader booksReader = new BufferedReader(new FileReader(new File(FILES_PATH + "books.txt")));
        BufferedReader authorsReader = new BufferedReader(new FileReader(new File(FILES_PATH + "authors.txt")));
        BufferedReader categoriesReader = new BufferedReader(new FileReader(new File(FILES_PATH + "categories.txt")));


        List<Author> authors = new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        while (true) {
            String authorLine = authorsReader.readLine();

            if (authorLine == null) {
                break;
            }

            if (authorLine.equals("")) {
                continue;
            }

            String[] tokens = authorLine.split(" ");

            String firstName = tokens[0];
            String lastName = tokens[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authorRepository.save(author);
        }
        authorRepository.findAll().forEach(authors::add);

        while (true) {
            String categoryLine = categoriesReader.readLine();

            if (categoryLine == null) {
                break;
            }

            if (categoryLine.equals("")) {
                continue;
            }

            String name = categoryLine.split(" ")[0];
            Category category = new Category();
            category.setName(name);

            categoryRepository.save(category);
        }
        categoryRepository.findAll().forEach(categories::add);

        while (true) {
            String bookLine = booksReader.readLine();
            if (bookLine == null){
                break;
            }

            if (bookLine.equals("")) {
                continue;
            }
            String[] data = bookLine.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            int categoryIndex = random.nextInt(categories.size());
            Category category = categories.get(categoryIndex);

            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }

            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(Set.of(category));

            bookService.save(book);

        }
    }
}
