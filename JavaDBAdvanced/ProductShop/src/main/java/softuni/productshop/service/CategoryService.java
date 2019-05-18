package softuni.productshop.service;

import softuni.productshop.domain.dtos.CategorySeedDto;

import java.io.FileNotFoundException;

public interface CategoryService {
    String saveCategory(CategorySeedDto userSeedDto);
}
