package softuni.gamestore.services;

import softuni.gamestore.domain.dtos.GameNewDto;
import softuni.gamestore.domain.entities.User;

public interface GameService {
    String addGame(GameNewDto gameNewDto, User user);
}
