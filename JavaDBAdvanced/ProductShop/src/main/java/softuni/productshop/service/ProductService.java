package softuni.productshop.service;

import softuni.productshop.domain.dtos.ProductSeedDto;
import softuni.productshop.domain.dtos.ProductSimpleViewDto;
import softuni.productshop.domain.entities.Product;

import java.util.List;

public interface ProductService {
    String saveProduct(ProductSeedDto productSeedDtos);

    List<ProductSimpleViewDto> productsInRange();

    Product getProduct(Integer id);

    int size();
}
