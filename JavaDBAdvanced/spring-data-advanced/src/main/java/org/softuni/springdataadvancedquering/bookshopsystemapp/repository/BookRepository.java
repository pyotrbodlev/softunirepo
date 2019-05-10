package org.softuni.springdataadvancedquering.bookshopsystemapp.repository;

import org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal price1, BigDecimal price2);

    @Query("select b from org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.Book as b where releaseDate NOT LIKE concat(:year, '%')")
    List<Book> findAllByReleaseDate_YearNotLike(@Param("year") int year);

    List<Book> findAllByTitleContaining(String pattern);

    List<Book> findAllByAuthorIn(List<Author> authors);

    @Query("SELECT b from org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.Book as b where length(title) >= :length")
    List<Book> findAllByTitleLongerThan(@Param("length") int length);

    List<ReducedBook> findAllByTitleEquals(String title);

    List<Book> findAllByCopiesLessThan(int copies);

}
