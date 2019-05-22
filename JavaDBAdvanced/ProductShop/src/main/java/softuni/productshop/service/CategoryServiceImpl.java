package softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.productshop.domain.dtos.CategoryByProductsDto;
import softuni.productshop.domain.dtos.CategorySeedDto;
import softuni.productshop.domain.entities.Category;
import softuni.productshop.domain.entities.Product;
import softuni.productshop.repositories.CategoryRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
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

    @Override
    public List<CategoryByProductsDto> getCategoriesByProductCount() {
        return this.categoryRepository.findAllOrderByProductsCount()
                .stream()
                .filter(c -> c.getProducts().size() > 0)
                .map(c -> {
                    CategoryByProductsDto dto = this.modelMapper.map(c, CategoryByProductsDto.class);
                    dto.setCategory(c.getName());
                    dto.setProductCount(c.getProducts().size());

                    List<Product> products = c.getProducts();

                    BigDecimal totalPrice = products.stream()
                            .map(Product::getPrice)
                            .reduce(BigDecimal::add)
                            .orElse(BigDecimal.ZERO);

                    dto.setAveragePrice(totalPrice
                            .divide(BigDecimal.valueOf(products.size()), 2, RoundingMode.HALF_UP));
                    dto.setTotalRevenue(totalPrice);

                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public Category getCategory(Integer id) {
        return this.categoryRepository.getById(id);
    }

    @Override
    public int size() {
        return (int) this.categoryRepository.count();
    }

    @Override
    public String addProductToCategory(Category category, Product product) {
        if(category == null || product == null){
            return "False category or product";
        }

        if (category.getProducts() == null){
            category.setProducts(new ArrayList<>());
        }

        category.getProducts().add(product);

        this.categoryRepository.save(category);

        return String.format("To %s was added %s category", product.getName(), category.getName());
    }
}
