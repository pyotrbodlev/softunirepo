package org.softuni.springdataadvancedquering.bookshopsystemapp.service;

import org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.Author;
import org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.Book;
import org.softuni.springdataadvancedquering.bookshopsystemapp.repository.AuthorRepository;
import org.softuni.springdataadvancedquering.bookshopsystemapp.util.FileLoader;
import org.softuni.springdataadvancedquering.bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH = "C:\\Users\\disel\\Documents\\GitHub\\softunirepo\\JavaDBAdvanced\\spring-data-advanced\\src\\main\\resources\\files\\authors.txt";

    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, EntityManager entityManager, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(FileLoader.getFilePath(Author.class));
        for (String line : authorFileContent) {
            String[] names = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    /**
     * 6. Authors Search
     * Write a program that prints the names of those authors, whose first name ends with a given string.
     *
     * @param pattern
     * @return
     */
    @Override
    public List<String> getAuthorsFullNameWhereFirstNameEndsWith(String pattern) {
        return this.authorRepository.findAllByFirstNameEndingWith(pattern).stream().map(Author::getFullName).collect(Collectors.toList());
    }

    /**
     * 10. Total Book Copies
     * Write a program that prints the total number of book copies by author. Order the results descending by total book copies.
     *
     * @return Collection of Author full names and count of book copies
     */
    @Override
    public List<String> getAllAuthorsOrderedByBooksCount() {
        return this.authorRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(a -> a.getBooks().stream().mapToInt(Book::getCopies).sum(), Comparator.reverseOrder()))
                .map(a -> String.format("%s %s", a.getFullName(), a.getBooks().stream().mapToInt(Book::getCopies).sum()))
                .collect(Collectors.toList());
    }

    /**
     * 14. Stored Procedure
     * @param fullName receives an author’s name
     * @return total number of books the author has written
     */
    @Override
    public BigInteger getBookCount(String fullName) {
        try {
            Object count = this.entityManager.createNamedStoredProcedureQuery("getAuthorsBookCount").setParameter("full_name", fullName).getSingleResult();

            return (BigInteger) count;
        } catch (NoResultException nre) {
            return BigInteger.ZERO;
        }
    }

}
