package org.softuni.springdataadvancedquering.bookshopsystemapp.service;

import org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.*;
import org.softuni.springdataadvancedquering.bookshopsystemapp.repository.AuthorRepository;
import org.softuni.springdataadvancedquering.bookshopsystemapp.repository.BookRepository;
import org.softuni.springdataadvancedquering.bookshopsystemapp.repository.CategoryRepository;
import org.softuni.springdataadvancedquering.bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH = "C:\\Users\\disel\\Documents\\GitHub\\softunirepo\\JavaDBAdvanced\\spring-data-advanced\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toSet());
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }


    /**
     * Exercises: Spring Data Advanced Querying
     * Problem N1 - Books Titles by Age Restriction
     *
     * @param ageRestriction
     * @return
     */
    @Override
    public List<String> getBooksTitlesByAgeRestriction(String ageRestriction) {
        AgeRestriction currentAgeRestriction = AgeRestriction.valueOf(ageRestriction.toUpperCase());

        List<Book> allByAgeRestriction = this.bookRepository.findAllByAgeRestriction(currentAgeRestriction);

        return allByAgeRestriction.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    /**
     * 2. Golden Books;
     *
     * @return List with books titles;
     */
    @Override
    public List<String> getGoldenBookTitles() {
        EditionType editionType = EditionType.GOLD;
        int copies = 5000;

        return this.bookRepository
                .findAllByEditionTypeAndCopiesLessThan(editionType, copies)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    /**
     * 3. Books by Price
     * Write a program that prints the titles and prices of books with price lower than 5 and higher than 40.
     *
     * @param lowerPrice
     * @param greaterPrice
     * @return
     */
    @Override
    public List<String> getBookTitleAndPrice(BigDecimal lowerPrice, BigDecimal greaterPrice) {
        return this.bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(lowerPrice, greaterPrice)
                .stream()
                .map(b -> String.format("%s - $%.2f", b.getTitle(), b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksTitlesNotInYear(int year) {
        return this.bookRepository.findAllByReleaseDate_YearNotLike(year).stream().map(Book::getTitle).collect(Collectors.toList());
    }

    /**
     * 5. Books Released Before Date
     * Write a program that prints the title, the edition type and the price of books, which are released before a given date. The date will be in the format dd-MM-yyyy.
     *
     * @param date
     * @return
     */
    @Override
    public List<String> getBooksTitlesBeforeDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = LocalDate.parse(date, formatter);

        return this.bookRepository.findAllByReleaseDateBefore(localDate)
                .stream()
                .map(b -> String.format("%s %s - $%.2f", b.getTitle(), b.getEditionType(), b.getPrice()))
                .collect(Collectors.toList());
    }

    /**
     * 7. Books Search
     * Write a program that prints the titles of books, which contain a given string (regardless of the casing).
     *
     * @param pattern
     * @return
     */
    @Override
    public List<String> getBookTitlesContaining(String pattern) {
        return this.bookRepository.findAllByTitleContaining(pattern)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    /**
     * 8. Book Titles Search
     * Write a program that prints the titles of books, which are written by authors, whose last name starts with a given string.
     *
     * @param pattern
     * @return
     */
    @Override
    public List<String> getBookTitlesByAuthorLastNameEndsWith(String pattern) {
        List<Author> authors = this.authorRepository.findAllByLastNameStartingWith(pattern);

        return this.bookRepository.findAllByAuthorIn(authors).stream().map(b -> String.format("%s (%s)", b.getTitle(), b.getAuthor().getFullName())).collect(Collectors.toList());
    }

    /**
     * 9. Count Books
     * Write a program that prints the number of books, whose title is longer than a given number.
     *
     * @param length of the String
     * @return count of Book titles that are longer than @length
     */
    @Override
    public int getCountBookTitleLongerThan(int length) {
        List<Book> books = this.bookRepository.findAllByTitleLongerThan(length);

        return books.size();
    }

    /**
     * 11. Reduced Book
     * Write a program that prints information (title, edition type, age restriction and price) for a book by given title.
     * When retrieving the book information select only those fields and do NOT include any other information in the returned result.
     * @param title of the book
     * @return collection of result String
     */
    @Override
    public List<String> getAllBooksByTitle(String title) {
        return this.bookRepository.findAllByTitleEquals(title)
                .stream()
                .map(b -> String.format("%s - %s - %s - %.2f", b.getTitle(), b.getEditionType(), b.getAgeRestriction(), b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllTitles(){
        return this.bookRepository.findAll().stream().map(Book::getTitle).collect(Collectors.toList());
    }
}
