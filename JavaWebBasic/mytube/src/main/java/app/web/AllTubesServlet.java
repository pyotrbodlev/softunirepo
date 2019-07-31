package app.web;

import app.repository.TubeRepository;
import app.services.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/all")
public class AllTubesServlet extends HttpServlet {
    private TubeService tubeService;

    @Inject
    public AllTubesServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tubes", this.tubeService.getAll());

        req.getRequestDispatcher("views/all-tubes.jsp").forward(req, resp);
    }
}
