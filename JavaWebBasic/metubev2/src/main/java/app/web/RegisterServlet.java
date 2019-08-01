package app.web;

import app.domain.entities.User;
import app.domain.models.UserRegisterModel;
import app.service.UserService;
import app.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Inject
    public RegisterServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("isLogged") == null || !((boolean) req.getSession().getAttribute("isLogged"))) {
            req.getRequestDispatcher("views/register.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegisterModel userRegisterModel = new UserRegisterModel(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"));

        User user = this.userService.save(userRegisterModel);

        if (user != null) {
            req.getSession().setAttribute("isLogged", true);
            req.getSession().setAttribute("loggedUser", user);
        }
        //TODO need to implement user existing answer
        resp.sendRedirect("/home");
    }
}
