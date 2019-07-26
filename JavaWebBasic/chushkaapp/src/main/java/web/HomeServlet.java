package web;

import service.ProductService;
import utils.parser.HtmlParser;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private ProductService productService;

    @Inject
    public HomeServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = HtmlParser.toHtml("views/index");
        resp.setHeader("Content-Type", "text/html");
        resp.getWriter().write(html);
    }

    @Override
    public void destroy() {
        this.productService.closeConnection();
    }
}
