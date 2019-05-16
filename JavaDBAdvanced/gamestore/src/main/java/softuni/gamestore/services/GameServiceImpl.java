package softuni.gamestore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.domain.dtos.*;
import softuni.gamestore.domain.entities.Game;
import softuni.gamestore.domain.entities.User;
import softuni.gamestore.repositories.GameRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String addGame(GameRegisterDto gameRegisterDto, UserActiveDto user) {
        if (user == null) {
            return "No logged user";
        }

        if (!user.getRole().name().equals("ADMIN")) {
            return user.getFullName() + " is not Admin!";
        }

        return this.addGame(gameRegisterDto);
    }

    @Override
    public String addGame(GameRegisterDto gameRegisterDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<GameRegisterDto>> validate = validator.validate(gameRegisterDto);

        if (validate.size() > 0) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<GameRegisterDto> gameNewDtoConstraintViolation : validate) {
                sb.append(gameNewDtoConstraintViolation.getMessage()).append(System.lineSeparator());
            }

            return sb.toString().trim();
        }

        Game game = this.modelMapper.map(gameRegisterDto, Game.class);

        this.gameRepository.saveAndFlush(game);

        return String.format("Added %s", game.getTitle());
    }

    @Override
    public String editGame(GameEditDto gameEditDto, UserActiveDto user) {
        if (user == null) {
            return "No logged user";
        }

        if (!user.getRole().name().equals("ADMIN")) {
            return user.getFullName() + " is not Admin!";
        }

        return this.editGame(gameEditDto);
    }

    @Override
    public String editGame(GameEditDto gameEditDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<GameEditDto>> validate = validator.validate(gameEditDto);

        if (validate.size() > 0) {
            return validate
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        Game game = this.gameRepository.findByTitle(gameEditDto.getTitle()).orElse(null);

        if (game == null) {
            return "No such game in store";
        }

        for (Map.Entry<String, String> entry : gameEditDto.getParams().entrySet()) {
            switch (entry.getKey()) {
                case "price":
                    game.setPrice(BigDecimal.valueOf(Double.parseDouble(entry.getValue())));
                    break;
                case "size":
                    game.setSize(Double.parseDouble(entry.getValue()));
                    break;
                case "trailer":
                    game.setTitle(entry.getValue());
                    break;
                case "thumbnailUrl":
                    game.setImageThumbnail(entry.getValue());
                    break;
                case "description":
                    game.setDescription(entry.getValue());
                    break;
            }
        }

        this.gameRepository.save(game);

        return "Edited " + game.getTitle();
    }

    @Override
    public String deleteGame(GameDeleteDto gameDeleteDto, UserActiveDto user) {
        if (user == null) {
            return "No logged user";
        }

        if (!user.getRole().name().equals("ADMIN")) {
            return user.getFullName() + " is not Admin!";
        }

        return this.deleteGame(gameDeleteDto);
    }

    @Override
    public String deleteGame(GameDeleteDto gameDeleteDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<GameDeleteDto>> validate = validator.validate(gameDeleteDto);

        if (validate.size() > 0) {
            return validate
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        Game game = this.gameRepository.findByTitle(gameDeleteDto.getTitle()).orElse(null);

        if (game == null) {
            return "No such game in store";
        }

        this.gameRepository.delete(game);

        return "Deleted " + game.getTitle();
    }

    @Override
    public String getAllGames() {
        return this.gameRepository.findAll()
                .stream()
                .map(g -> modelMapper.map(g, GameSimpleViewDto.class))
                .map(sg -> String.format("%s %s", sg.getTitle(), sg.getPrice()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String getDetailedInfo(String title) {
        Game game = this.gameRepository.findByTitle(title).orElse(null);

        if (game == null){
            return "No such game in Database";
        }

        GameDetailedViewDto gameDetailedViewDto = this.modelMapper.map(game, GameDetailedViewDto.class);

        return String.format("Title: %s", gameDetailedViewDto.getTitle()) + System.lineSeparator() +
                String.format("Price: %s", gameDetailedViewDto.getPrice()) + System.lineSeparator() +
                String.format("Description: %s", gameDetailedViewDto.getDescription()) + System.lineSeparator() +
                String.format("Release date: %s", gameDetailedViewDto.getReleaseDate());
    }
}
