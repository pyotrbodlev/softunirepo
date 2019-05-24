package softuni.cardealer.parsers;

import java.io.File;
import java.io.FileNotFoundException;

public interface JsonParser {
    <E> E fromJson(File file, Class<E> eClass) throws FileNotFoundException;

    <E> String toJson(E entity);
}
