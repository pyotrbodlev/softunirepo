package softuni.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.gamestore.domain.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
}
