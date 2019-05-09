package org.softuni.springdataadvancedquering.bookshopsystemapp.repository;

import org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
