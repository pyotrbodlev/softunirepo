package org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities;

import java.math.BigDecimal;

public interface ReducedBook {
    String getTitle();

    EditionType getEditionType();

    AgeRestriction getAgeRestriction();

    BigDecimal getPrice();

}
