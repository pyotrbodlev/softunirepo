package softuni.gamestore.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import softuni.gamestore.domain.dtos.UserActiveDto;
import softuni.gamestore.domain.dtos.UserRegistrationDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomParserJson {
    public List<UserRegistrationDto> parseToUserRegistrationDtos() throws FileNotFoundException {
        File file = new File(
                getClass().getClassLoader().getResource("users.json").getFile()
        );

        BufferedReader reader = new BufferedReader(new FileReader(file));

        Gson gson = new GsonBuilder().create();

        UserRegistrationDto[] userRegistrationDtos = gson.fromJson(reader, UserRegistrationDto[].class);

        return Arrays.asList(userRegistrationDtos);
    }
}
