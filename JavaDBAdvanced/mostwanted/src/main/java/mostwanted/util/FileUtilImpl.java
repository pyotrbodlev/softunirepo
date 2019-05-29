package mostwanted.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder sb = new StringBuilder();

        while (true){
            String line = reader.readLine();

            if (line == null){
                break;
            }

            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
