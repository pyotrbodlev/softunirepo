package web;

import domain.Product;
import domain.Type;
import service.ProductService;
import utils.parser.HtmlParser;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create/product")
public class RegistrationServlet extends HttpServlet {
    private ProductService productService;

    @Inject
    public RegistrationServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = HtmlParser.toHtml("views/create-product");
        resp.getWriter().write(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = HtmlParser.toHtml("views/successful-reg");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String type = req.getParameter("type");

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setType(Type.valueOf(type));

        this.productService.save(product);

        resp.getWriter().write(html.replace("{productName}", name + " " + type));
    }
}
