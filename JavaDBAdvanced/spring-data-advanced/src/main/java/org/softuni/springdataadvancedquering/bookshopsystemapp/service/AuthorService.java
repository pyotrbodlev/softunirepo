package org.softuni.springdataadvancedquering.bookshopsystemapp.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    List<String> getAuthorsFullNameWhereFirstNameEndsWith(String pattern);

    List<String> getAllAuthorsOrderedByBooksCount();

    BigInteger getBookCount(String fullName);
}
