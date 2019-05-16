package softuni.gamestore.configuration;

import org.modelmapper.Converters;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.gamestore.domain.dtos.UserActiveDto;
import softuni.gamestore.domain.entities.Game;
import softuni.gamestore.domain.entities.User;

import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Configuration
public class JavaConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public ModelMapper modelMapper(){
       return new ModelMapper();
    }
}
