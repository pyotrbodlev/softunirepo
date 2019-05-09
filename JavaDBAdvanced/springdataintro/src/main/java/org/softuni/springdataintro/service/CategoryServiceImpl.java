package org.softuni.springdataintro.service;

import org.softuni.springdataintro.domain.Category;
import org.softuni.springdataintro.repositories.CategoryRepository;
import org.softuni.springdataintro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_FILE_PATH = "C:\\Users\\admin\\Documents\\GitHub\\softunirepo\\JavaDBAdvanced\\springdataintro\\src\\main\\resources\\categories.txt";

    private CategoryRepository categoryRepository;
    private FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategory() throws IOException {
        List<String> data = fileUtil.readData(CATEGORY_FILE_PATH);

        for (String name : data) {

            Category category = new Category();
            category.setName(name);

            categoryRepository.saveAndFlush(category);
        }
    }
}
