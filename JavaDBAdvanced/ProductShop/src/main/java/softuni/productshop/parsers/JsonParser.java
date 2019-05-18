package softuni.productshop.parsers;

import java.io.File;
import java.io.FileNotFoundException;

public interface JsonParser {
    <E> E parseFromJson(File file, Class<E> type) throws FileNotFoundException;

    <E> String toJson(E entity);
}
