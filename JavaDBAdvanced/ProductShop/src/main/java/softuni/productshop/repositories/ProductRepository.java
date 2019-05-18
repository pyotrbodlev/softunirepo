package softuni.productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.productshop.domain.entities.Product;
import softuni.productshop.domain.entities.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal lowerRange, BigDecimal higherRange);

    @Query("SELECT p FROM Product  p WHERE p.buyer NOT LIKE NULL")
    List<Product> findAllWhereBuyerNotNull();

    Product getById(Integer id);
}
