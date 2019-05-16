package softuni.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.gamestore.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);

}
