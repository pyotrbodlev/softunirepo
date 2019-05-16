package softuni.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.gamestore.domain.entities.Order;
import softuni.gamestore.domain.entities.Status;
import softuni.gamestore.domain.entities.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Order findByUserAndStatus(User user, Status status);
}
