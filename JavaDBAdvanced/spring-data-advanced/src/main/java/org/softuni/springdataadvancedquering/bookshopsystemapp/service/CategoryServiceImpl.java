package org.softuni.springdataadvancedquering.bookshopsystemapp.service;

import org.softuni.springdataadvancedquering.bookshopsystemapp.domain.entities.Category;
import org.softuni.springdataadvancedquering.bookshopsystemapp.util.FileLoader;
import org.softuni.springdataadvancedquering.bookshopsystemapp.util.FileUtil;
import org.softuni.springdataadvancedquering.bookshopsystemapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String CATEGORIES_FILE_PATH = "C:\\Users\\disel\\Documents\\GitHub\\softunirepo\\JavaDBAdvanced\\spring-data-advanced\\src\\main\\resources\\files\\categories.txt";

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] categoriesFileContent = this.fileUtil.getFileContent(FileLoader.getFilePath(Category.class));
        for (String line : categoriesFileContent) {
            Category category = new Category();
            category.setName(line);

            this.categoryRepository.saveAndFlush(category);
        }
    }
}
