package app.web;

import app.domain.entities.Tube;
import app.services.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {
    private TubeService tubeService;

    @Inject
    public DetailsServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getQueryString().split("=")[1];

        Tube tube = this.tubeService.findByTitle(title);

        req.setAttribute("tube", tube);

        req.getRequestDispatcher("views/details-tube.jsp").forward(req, resp);
    }
}
