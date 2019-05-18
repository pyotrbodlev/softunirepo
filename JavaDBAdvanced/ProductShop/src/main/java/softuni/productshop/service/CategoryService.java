package softuni.productshop.service;

import softuni.productshop.domain.dtos.CategoryByProductsDto;
import softuni.productshop.domain.dtos.CategorySeedDto;
import softuni.productshop.domain.entities.Category;
import softuni.productshop.domain.entities.Product;

import java.io.FileNotFoundException;
import java.util.List;

public interface CategoryService {
    String saveCategory(CategorySeedDto userSeedDto);

    List<CategoryByProductsDto> getCategoriesByProductCount();

    Category getCategory(Integer id);

    int size();

    String addProductToCategory(Category category, Product product);
}
