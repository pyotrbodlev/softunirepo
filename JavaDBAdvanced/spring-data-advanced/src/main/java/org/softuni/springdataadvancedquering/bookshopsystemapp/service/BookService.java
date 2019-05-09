package org.softuni.springdataadvancedquering.bookshopsystemapp.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<String> getBooksTitlesByAgeRestriction(String ageRestriction);

    List<String> getGoldenBookTitles();

    List<String> getBookTitleAndPrice(BigDecimal lowerPrice, BigDecimal greaterPrice);

    List<String> getBooksTitlesNotInYear(int year);

    List<String> getBooksTitlesBeforeDate(String date);

    List<String> getBookTitlesContaining(String pattern);

    List<String> getBookTitlesByAuthorLastNameEndsWith(String pattern);

    int getCountBookTitleLongerThan(int length);

    List<String> getAllBooksByTitle(String title);

    List<String> getAllTitles();
}
