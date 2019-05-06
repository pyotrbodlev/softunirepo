package org.softuni.springdataintro.service;

import org.softuni.springdataintro.domain.Author;
import org.softuni.springdataintro.domain.Book;
import org.softuni.springdataintro.domain.Category;
import org.softuni.springdataintro.domain.enums.AgeRestriction;
import org.softuni.springdataintro.domain.enums.EditionType;
import org.softuni.springdataintro.repositories.AuthorRepository;
import org.softuni.springdataintro.repositories.BookRepository;
import org.softuni.springdataintro.repositories.CategoryRepository;
import org.softuni.springdataintro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private static final String BOOKS_FILE_PATH = "C:\\Users\\admin\\Documents\\GitHub\\softunirepo\\JavaDBAdvanced\\springdataintro\\src\\main\\resources\\books.txt";

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    public void seedBooks() throws IOException, ParseException {
        List<String> data = fileUtil.readData(BOOKS_FILE_PATH);
        for (String line : data) {
            String[] tokens = line.split("\\s+");

            Author author = this.getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(tokens[1]);
            int copies = Integer.parseInt(tokens[2]);
            BigDecimal price = new BigDecimal(tokens[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];
            StringBuilder titleBuilder = new StringBuilder();
            Category category = this.getRandomCategory();

            for (int i = 5; i < tokens.length; i++) {
                titleBuilder.append(tokens[i]).append(" ");
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

            this.bookRepository.saveAndFlush(book);
        }
    }

    private Category getRandomCategory() {
        Random random = new Random();
        return this.categoryRepository.findAll().get(random.nextInt((int) categoryRepository.count()));
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        return this.authorRepository.findAll().get(random.nextInt((int) authorRepository.count()));
    }

    /**
     * Problem N1 - Get all books after the year 2000. Print only their titles.
     */
    @Override
    public void getAllBooksAfter2000() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date date = formatter.parse("2000");

        List<Book> allByReleaseDate_yearAfter = this.bookRepository.findAllByReleaseDateAfter(date);

        for (Book book : allByReleaseDate_yearAfter) {
            System.out.println(book.getTitle());
        }
    }
}
