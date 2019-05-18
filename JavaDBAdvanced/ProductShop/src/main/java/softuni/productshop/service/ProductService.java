package softuni.productshop.service;

import softuni.productshop.domain.dtos.CategorySeedDto;
import softuni.productshop.domain.dtos.ProductSeedDto;
import softuni.productshop.domain.dtos.ProductSimpleViewDto;
import softuni.productshop.domain.dtos.ProductWithBuyerDto;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductService {
    String saveProduct(ProductSeedDto productSeedDtos);

    List<ProductSimpleViewDto> productsInRange();
}
