package web;

import com.google.gson.Gson;
import domain.Product;
import service.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products/all/export")
public class AllProducts extends HttpServlet {
    private ProductService productService;
    private Gson gson;

    @Inject
    public AllProducts(ProductService productService) {
        this.productService = productService;
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> allProducts = this.productService.getAllProducts();
        String toJson = this.gson.toJson(allProducts);

        resp.setHeader("Content-Type", "application/json");

        resp.getWriter().write(toJson);
    }
}
