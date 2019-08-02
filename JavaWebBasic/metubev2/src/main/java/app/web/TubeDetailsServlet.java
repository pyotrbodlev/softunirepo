package app.web;

import app.domain.entities.Tube;
import app.service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/details")
public class TubeDetailsServlet extends HttpServlet {
    private TubeService tubeService;

    @Inject
    public TubeDetailsServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Tube tube = tubeService.findById(req.getQueryString().split("=")[1]);

        if (tube != null) {
            req.setAttribute("tube", tube);
            req.getRequestDispatcher("views/details.jsp").forward(req, resp);
        }

        //TODO implement details page
    }
}
