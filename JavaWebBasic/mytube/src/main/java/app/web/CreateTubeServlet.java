package app.web;

import app.domain.dtos.TubeRegDto;
import app.domain.entities.Tube;
import app.services.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateTubeServlet extends HttpServlet {
    private ModelMapper modelMapper;
    private TubeService tubeService;

    @Inject
    public CreateTubeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeRegDto tubeRegDto = new TubeRegDto(req.getParameter("title").replaceAll("\\s", "_"),
                req.getParameter("description"),
                req.getParameter("link"),
                req.getParameter("uploader"));

        Tube tube = this.modelMapper.map(tubeRegDto, Tube.class);

        this.tubeService.save(tube);

        resp.sendRedirect("/all");
    }
}
