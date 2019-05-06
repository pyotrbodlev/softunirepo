package org.softuni.springdataintro.service;

import org.softuni.springdataintro.domain.Author;
import org.softuni.springdataintro.repositories.AuthorRepository;
import org.softuni.springdataintro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "C:\\Users\\admin\\Documents\\GitHub\\softunirepo\\JavaDBAdvanced\\springdataintro\\src\\main\\resources\\authors.txt";

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
}
