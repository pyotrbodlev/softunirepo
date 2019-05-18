package softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.productshop.domain.dtos.ProductSeedDto;
import softuni.productshop.domain.dtos.ProductSimpleViewDto;
import softuni.productshop.domain.entities.Product;
import softuni.productshop.repositories.ProductRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ModelMapper modelMapper;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public String saveProduct(ProductSeedDto productSeedDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<ProductSeedDto>> validate = validator.validate(productSeedDto);

        if (validate.size() > 0) {
            return validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(System.lineSeparator()));
        }

        Product product = this.modelMapper.map(productSeedDto, Product.class);

        this.productRepository.save(product);

        return product.getName() + " was added to Database!";

    }

    /**
     * Query 1 - Products In Range
     * Get all products in a specified price range (e.g. 500 to 1000), which have no buyer.
     * Order them by price (from lowest to highest).
     * Select only the product name, price and the full name of the seller. Export the result to JSON.
     *
     * @return JSON string with result set
     */
    @Override
    public List<ProductSimpleViewDto> productsInRange() {
        return this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal.valueOf(500.00), BigDecimal.valueOf(1000.00))
                .stream()
                .map(product -> {
                    ProductSimpleViewDto simpleViewDto = this.modelMapper.map(product, ProductSimpleViewDto.class);
                    simpleViewDto.setSellerFullName(product.getSeller() == null ? null : product.getSeller().getFirstName() + " " + product.getSeller().getLastName());
                    return simpleViewDto;
                })
                .collect(Collectors.toList());
    }
}
