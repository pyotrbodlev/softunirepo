import domain.Cat;
import domain.CatRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/home/peter/IdeaProjects/javaeeintrodemo/src/main/resources/all-cats.html";

        String html = HtmlParser.getHtml(path);

        if (CatRepository.getAll().isEmpty()) {
            resp.getWriter().println(String.format(html, "<h1>There are no cats. <a href=\"\\cats\\create\">Create some!<a><br/>"));
        } else {
            StringBuilder cats = new StringBuilder();
            for (Cat cat : CatRepository.getAll()) {
                cats.append(String.format("<a href=\"/cats/profile?name=%s\">%s</a><br/>", cat.getName(), cat.getName()));
            }

            resp.getWriter().println(String.format(html, cats.toString()));
        }
    }
}
