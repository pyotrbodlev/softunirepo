import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class HtmlParser {
    public static String getHtml(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

        StringBuilder sb = new StringBuilder();

        while (true){
            String line = reader.readLine();

            if (line == null){
                break;
            }

            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
