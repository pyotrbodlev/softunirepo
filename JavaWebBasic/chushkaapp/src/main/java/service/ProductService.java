package service;

import domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    void closeConnection();

    void save(Product product);

    Product findByName(String productName);
}
