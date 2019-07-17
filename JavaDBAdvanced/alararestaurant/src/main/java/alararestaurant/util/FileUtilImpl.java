package alararestaurant.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) {
        File file = new File(filePath);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();

            while (true) {
                String line = reader.readLine();

                if (line == null) {
                    break;
                }

                sb.append(line).append(System.lineSeparator());
            }

            return sb.toString().trim();

        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
