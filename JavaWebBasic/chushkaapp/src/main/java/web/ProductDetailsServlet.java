package web;

import domain.Product;
import service.ProductService;
import utils.parser.HtmlParser;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {
    private ProductService productService;

    @Inject
    public ProductDetailsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = HtmlParser.toHtml("views/details-product");
        String productName = req.getQueryString().split("=")[1];
        Product product = this.productService.findByName(productName);

        resp.getWriter().write(
                html
                        .replaceAll("\\{productName}", productName)
                        .replace("{description}", product.getDescription())
                        .replace("{type}", product.getType().toString()));
    }
}
