package softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.productshop.domain.dtos.CategorySeedDto;
import softuni.productshop.domain.dtos.UserSeedDto;
import softuni.productshop.domain.entities.Category;
import softuni.productshop.domain.entities.User;
import softuni.productshop.parsers.JsonParser;
import softuni.productshop.repositories.CategoryRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveCategory(CategorySeedDto categorySeedDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<CategorySeedDto>> validate = validator.validate(categorySeedDto);

        if (validate.size() > 0) {
            return validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(System.lineSeparator()));
        }

        Category category = this.modelMapper.map(categorySeedDto, Category.class);

        this.categoryRepository.save(category);

        return category.getName() + " was added to Database!";

    }


}
