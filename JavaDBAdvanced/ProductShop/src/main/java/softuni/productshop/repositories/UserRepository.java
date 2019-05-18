package softuni.productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.productshop.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);

    @Query("SELECT u FROM User u WHERE u.productsToSell.size > 0 ORDER BY u.firstName ASC, u.lastName ASC")
    List<User> findByProductsToSellIsNotEmpty();
}
