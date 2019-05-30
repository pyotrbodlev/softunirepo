package org.softuni.springdataintro.service;

import org.softuni.springdataintro.domain.Author;
import org.softuni.springdataintro.repositories.AuthorRepository;
import org.softuni.springdataintro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final String AUTHORS_FILE_PATH = getClass().getClassLoader().getResource("authors.txt").getFile();

    private AuthorRepository authorRepository;
    private FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        List<String> data = this.fileUtil.readData(AUTHORS_FILE_PATH);

        for (String line : data) {
            String[] tokens = line.split("\\s+");
            String firstName = tokens[0];
            String lastName = tokens[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authorRepository.saveAndFlush(author);
        }
    }

    /**
     * Problem N2 - Get all authors with at least one book with release date before 1990. Print their first name and last name.
     * @return list with authors.
     */
    @Override
    public List<Author> getAuthorsWithBookBefore() {

        return this.authorRepository.findAll()
                .stream().filter(a -> {
                    int size = (int) a.getBooks().stream().filter(b -> {
                        try {
                            return b.getReleaseDate().before(new SimpleDateFormat("yyyy").parse("1990"));
                        } catch (ParseException e) {
                            System.out.println(e.getMessage());
                        }
                        return false;
                    }).count();

                    return size >= 1;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Author> getAuthorsOrderedByBooksCount(){
        return this.authorRepository.getAllOrderedByBooksCount();
    }
}
