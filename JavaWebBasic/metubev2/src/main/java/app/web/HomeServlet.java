package app.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("isLogged") == null || !((boolean) req.getSession().getAttribute("isLogged"))) {
            resp.sendRedirect("/");
        } else {
            req.getRequestDispatcher("views/home.jsp").forward(req, resp);
        }
    }
}
