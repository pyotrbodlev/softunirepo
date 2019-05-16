package softuni.gamestore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.domain.dtos.UserActiveDto;
import softuni.gamestore.domain.entities.Game;
import softuni.gamestore.domain.entities.Order;
import softuni.gamestore.domain.entities.Status;
import softuni.gamestore.domain.entities.User;
import softuni.gamestore.repositories.GameRepository;
import softuni.gamestore.repositories.OrderRepository;
import softuni.gamestore.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private UserRepository userRepository;
    private GameRepository gameRepository;
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(UserRepository userRepository, GameRepository gameRepository, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String addOrder(UserActiveDto userActiveDto, String title){
        if (userActiveDto == null){
            return "No logged user";
        }

        if (this.gameRepository.findByTitle(title).isEmpty()){
            return "No such game in store";
        }

        User user = this.userRepository.findByEmail(userActiveDto.getEmail());

        if (user.getGames().stream().anyMatch(g -> g.getTitle().equals(title))){
            return "User already has this game";
        }

        Game game = this.gameRepository.findByTitle(title).orElse(null);

        Order order;

        if(this.orderRepository.findByUserAndStatus(user, Status.ACTIVE) == null){
            order = new Order();
            order.setStatus(Status.ACTIVE);
        } else {
            order = this.orderRepository.findByUserAndStatus(user, Status.ACTIVE);
        }

        if (order.getGames() == null){
            order.setGames(List.of(game));
        }else if(order.getGames().stream().anyMatch(g -> g.getTitle().equals(game.getTitle()))){
            return "Game is already in shopping cart";
        } else {
            order.getGames().add(game);
        }

        order.setUser(user);

        this.gameRepository.saveAndFlush(game);
        this.userRepository.saveAndFlush(user);
        this.orderRepository.saveAndFlush(order);

        return String.format("%s added to cart.", title);
    }

    @Override
    public String removeGameFromOrder(UserActiveDto userActiveDto, String title){
        if (userActiveDto == null){
            return "No logged user";
        }

        User user = this.userRepository.findByEmail(userActiveDto.getEmail());

        Order order = this.orderRepository.findByUserAndStatus(user, Status.ACTIVE);

        if (order == null){
            return "No active order";
        }

        Game game = this.gameRepository.findByTitle(title).orElse(null);

        if (game == null){
            return "No such game in shop";
        }

        if (order.getGames().stream().noneMatch(g -> g.getTitle().equals(game.getTitle()))){
            return "This game is not in cart";
        }

        Game game1 = order.getGames().stream().filter(g -> g.getTitle().equals(game.getTitle())).findFirst().get();

        order.getGames().remove(game1);

        this.orderRepository.saveAndFlush(order);

        return game.getTitle() + " removed from cart.";
    }

    @Override
    public String buyItems(UserActiveDto userActiveDto){
        if (userActiveDto == null){
            return "No logged user";
        }

        User user = this.userRepository.findByEmail(userActiveDto.getEmail());

        Order order = this.orderRepository.findByUserAndStatus(user, Status.ACTIVE);

        if (order == null){
            return "No active order";
        }

        if (order.getGames() == null
                || order.getGames().size() == 0){
            return "No games in shopping cart";
        }

        if (user.getGames() == null){
            user.setGames(new ArrayList<>());
        }

        user.getGames().addAll(order.getGames());
        order.setStatus(Status.CLOSED);


        String resultGames = order.getGames()
                .stream()
                .map(g -> String.format(" -%s", g.getTitle()))
                .collect(Collectors.joining(System.lineSeparator()));

        order.getGames().clear();

        this.userRepository.saveAndFlush(user);
        this.orderRepository.saveAndFlush(order);

        return "Successfully bought games: " + System.lineSeparator() + resultGames;
    }
}
