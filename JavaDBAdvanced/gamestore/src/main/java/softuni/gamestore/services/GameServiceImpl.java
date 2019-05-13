package softuni.gamestore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.domain.dtos.GameNewDto;
import softuni.gamestore.domain.entities.Game;
import softuni.gamestore.domain.entities.User;
import softuni.gamestore.repositories.GameRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

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
    public String addGame(GameNewDto gameNewDto, User user){
        if (!user.getRole().name().equals("ADMIN")){
            return user.getFullName() + " is not Admin!";
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<GameNewDto>> validate = validator.validate(gameNewDto);

        if (validate.size() > 0){
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<GameNewDto> gameNewDtoConstraintViolation : validate) {
                sb.append(gameNewDtoConstraintViolation.getMessage()).append(System.lineSeparator());
            }

            return sb.toString().trim();
        }

        Game game = this.modelMapper.map(gameNewDto, Game.class);

        this.gameRepository.saveAndFlush(game);

        return String.format("Added %s", game.getTitle());
    }
}
