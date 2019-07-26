package utils.parser;

import java.io.*;

public class HtmlParser {
    public static String toHtml(String viewPath) throws IOException {
        String path = String.format("/home/peter/IdeaProjects/chushkaapp/src/main/resources/%s.html", viewPath);

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
