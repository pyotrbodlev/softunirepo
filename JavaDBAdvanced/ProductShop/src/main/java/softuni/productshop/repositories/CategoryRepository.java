package softuni.productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.productshop.domain.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
