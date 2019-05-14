package softuni.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.gamestore.domain.entities.Game;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    Optional<Game> findByTitle(String title);

    Optional<Game> deleteGameByTitle(String title);
}
