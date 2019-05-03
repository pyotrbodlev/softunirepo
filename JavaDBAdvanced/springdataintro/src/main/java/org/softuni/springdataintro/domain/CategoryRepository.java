package org.softuni.springdataintro.domain;

import org.softuni.springdataintro.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
