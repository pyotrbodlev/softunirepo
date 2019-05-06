package org.softuni.springdataintro.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public List<String> readData(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = new ArrayList<>();
        while (true) {
            String line = reader.readLine();

            if (line == null) {
                break;
            }

            lines.add(line);
        }

        return lines.stream().filter(l -> !l.equals("")).collect(Collectors.toList());
    }
}
