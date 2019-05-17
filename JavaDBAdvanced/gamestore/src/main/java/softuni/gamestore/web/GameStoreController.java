package softuni.gamestore.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import softuni.gamestore.domain.dtos.*;
import softuni.gamestore.parsers.CustomParserJson;
import softuni.gamestore.services.GameService;
import softuni.gamestore.services.OrderService;
import softuni.gamestore.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {
    private UserActiveDto activeUser;
    private UserService userService;
    private GameService gameService;
    private OrderService orderService;
    private CustomParserJson parserJson;
    private Scanner scanner;

    public GameStoreController(UserService userService, GameService gameService, OrderService orderService, CustomParserJson parserJson, Scanner scanner) {
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
        this.parserJson = parserJson;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String line = this.scanner.nextLine();

            String[] tokens = line.split("\\|");

            switch (tokens[0]) {
                case "RegisterUser":
                    String email = tokens[1];
                    String password = tokens[2];
                    String confirmPassword = tokens[3];
                    String fullName = tokens[4];

                    UserRegistrationDto userRegistrationDto = new UserRegistrationDto(email, password, confirmPassword, fullName);

                    System.out.println(this.userService.registerUser(userRegistrationDto));

                    break;
                case "LoginUser":
                    email = tokens[1];
                    password = tokens[2];

                    if (this.activeUser != null) {
                        System.out.println("You must logout before login");
                        continue;
                    }

                    UserActiveDto loginUser = this.userService.loginUser(new UserLoginDto(email, password));

                    if (loginUser != null) {
                        this.activeUser = loginUser;
                        System.out.println("Successfully logged in " + loginUser.getFullName());
                    } else {
                        System.out.println("Incorrect email \\ password");
                    }
                    break;
                case "Logout":
                    if (this.activeUser == null) {
                        System.out.println("No user logged in");
                    } else {
                        fullName = this.activeUser.getFullName();
                        this.activeUser = null;
                        System.out.printf("User %s successfully logged out%n", fullName);
                    }
                    break;
                case "AddGame":
                    String title = tokens[1];
                    BigDecimal price = BigDecimal.valueOf(Double.parseDouble(tokens[2]));
                    double size = Double.parseDouble(tokens[3]);
                    String trailer = tokens[4];
                    String thumbnailUrl = tokens[5];
                    String description = tokens[6];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate localDate = LocalDate.parse(tokens[7], formatter);

                    GameRegisterDto gameRegisterDto = new GameRegisterDto(title, price, size, trailer, thumbnailUrl, description, localDate);

                    System.out.println(this.gameService.addGame(gameRegisterDto, this.activeUser));
                    break;
                case "EditGame":
                    title = tokens[1];
                    Map<String, String> params = getParams(tokens);

                    GameEditDto gameEditDto = new GameEditDto(title, params);

                    System.out.println(this.gameService.editGame(gameEditDto, this.activeUser));
                    break;
                case "DeleteGame":
                    title = tokens[1];
                    System.out.println(this.gameService.deleteGame(new GameDeleteDto(title), this.activeUser));
                    break;
                case "AllGame":
                    System.out.println(this.gameService.getAllGames());
                    break;
                case "DetailGame":
                    title = tokens[1];
                    System.out.println(this.gameService.getDetailedInfo(title));
                    break;
                case "OwnedGame":
                    this.activeUser.getOwnedGames().forEach(System.out::println);
                    break;
                case "AddItem":
                    title = tokens[1];
                    System.out.println(this.orderService.addOrder(this.activeUser, title));
                    break;
                case "RemoveItem":
                    title = tokens[1];
                    System.out.println(this.orderService.removeGameFromOrder(this.activeUser, title));
                    break;
                case "BuyItem":
                    System.out.println(this.orderService.buyItems(this.activeUser));
                    break;
                case "SeedUsers":
                    this.parserJson.parseToUserRegistrationDtos().forEach(ur -> {
                        System.out.println(this.userService.registerUser(ur));
                    });
                    break;
            }
        }
    }

    private Map<String, String> getParams(String[] tokens) {
        Map<String, String> params = new HashMap<>();

        for (int i = 2; i < tokens.length; i++) {
            String[] miniTokens = tokens[i].split("=");
            String key = miniTokens[0];
            String value = miniTokens[1];
            params.put(key, value);
        }
        return params;
    }

}
