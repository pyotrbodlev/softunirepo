package softuni.gamestore.services;

import softuni.gamestore.domain.dtos.*;
import softuni.gamestore.domain.entities.Game;
import softuni.gamestore.domain.entities.User;

import java.util.Optional;

public interface GameService {
    String addGame(GameRegisterDto gameRegisterDto, UserActiveDto user);

    String addGame(GameRegisterDto gameRegisterDto);

    String editGame(GameEditDto gameEditDto, UserActiveDto user);

    String editGame(GameEditDto gameEditDto);

    String deleteGame(GameDeleteDto gameDeleteDto, UserActiveDto user);

    String deleteGame(GameDeleteDto gameDeleteDto);

}
