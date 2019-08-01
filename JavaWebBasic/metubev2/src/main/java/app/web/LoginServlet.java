package app.web;

import app.domain.entities.User;
import app.domain.models.UserLoginModel;
import app.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Inject
    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("isLogged") == null || !((boolean) req.getSession().getAttribute("isLogged"))) {
            req.getRequestDispatcher("views/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/home");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserLoginModel userLoginModel = new UserLoginModel(username, password);

        User loggedUser = this.userService.login(userLoginModel);

        if (loggedUser == null){
            req.getRequestDispatcher("views/user-not-registered.jsp").forward(req, resp);
        }else {
            req.getSession().setAttribute("isLogged", true);
            req.getSession().setAttribute("loggedUser", loggedUser);

            resp.sendRedirect("/");
        }
    }
}
