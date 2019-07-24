import domain.Cat;
import domain.CatRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cats/create")
public class RegisterCatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/home/peter/IdeaProjects/javaeeintrodemo/src/main/resources/register.html";

        String html = HtmlParser.getHtml(path);

        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        String color = req.getParameter("color");
        int age = Integer.parseInt(req.getParameter("age"));

        Cat cat = new Cat(name, breed, color, age);

        CatRepository.addCat(cat);

        resp.sendRedirect(String.format("/cats/profile?name=%s", name));
    }
}
