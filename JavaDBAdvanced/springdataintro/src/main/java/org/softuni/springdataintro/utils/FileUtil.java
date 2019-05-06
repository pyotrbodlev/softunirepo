package org.softuni.springdataintro.utils;

import java.io.IOException;
import java.util.List;

public interface FileUtil {
    List<String> readData(String filePath) throws IOException;
}
