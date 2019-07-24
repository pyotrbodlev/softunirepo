import domain.Cat;
import domain.CatRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cats/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/home/peter/IdeaProjects/javaeeintrodemo/src/main/resources/profile.html";
        String name = req.getQueryString().split("=")[1];

        Cat cat = CatRepository.findByName(name);

        String html = HtmlParser.getHtml(path);

        if (cat != null) {
            resp.getWriter().println(String.format(html, cat.getName(), cat.getBreed(), cat.getColor(), cat.getAge()));
        } else {
            String div = html.substring(0, html.indexOf("<div>") + 5);
            String div1 = html.substring(html.lastIndexOf("</div>"));
            resp.getWriter().println(div + "<h1>Cat, with name - " + name + " was not found.</h1>" + div1);
        }
    }
}
