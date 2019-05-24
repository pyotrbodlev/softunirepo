package softuni.cardealer.parsers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class JsonParserImpl implements JsonParser {
    private final Gson gson;

    @Autowired
    public JsonParserImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <E> E fromJson(File file, Class<E> eClass) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        return this.gson.fromJson(bufferedReader, eClass);
    }

    @Override
    public <E> String toJson(E entity){
        return this.gson.toJson(entity);
    }
}
