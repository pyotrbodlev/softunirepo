package softuni.productshop.parsers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class JsonParserImpl implements JsonParser {
    private Gson gson;

    @Autowired
    public JsonParserImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <E> E parseFromJson(File file, Class<E> type) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        return this.gson.fromJson(reader, type);
    }

    @Override
    public <E> String toJson(E entity){
        return this.gson.toJson(entity);
    }
}
