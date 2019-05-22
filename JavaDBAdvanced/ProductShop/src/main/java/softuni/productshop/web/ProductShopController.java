package softuni.productshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.productshop.domain.dtos.CategorySeedDto;
import softuni.productshop.domain.dtos.ProductSeedDto;
import softuni.productshop.domain.dtos.UserSeedDto;
import softuni.productshop.domain.entities.Category;
import softuni.productshop.domain.entities.Product;
import softuni.productshop.domain.entities.User;
import softuni.productshop.parsers.JsonParser;
import softuni.productshop.service.CategoryService;
import softuni.productshop.service.ProductService;
import softuni.productshop.service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class ProductShopController implements CommandLineRunner {

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private JsonParser jsonParser;

    @Autowired
    public ProductShopController(UserService userService, CategoryService categoryService, ProductService productService, JsonParser jsonParser) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.jsonParser = jsonParser;
    }

    public void seedUsers() throws FileNotFoundException {
        File usersFile = new File(getClass().getClassLoader().getResource("files/users.json").getFile());

        UserSeedDto[] userSeedDtos = this.jsonParser.parseFromJson(usersFile, UserSeedDto[].class);

        Arrays.stream(userSeedDtos).forEach(userSeedDto -> System.out.println(this.userService.saveUser(userSeedDto)));
    }

    public void seedProducts() throws FileNotFoundException {
        File usersFile = new File(getClass().getClassLoader().getResource("files/products.json").getFile());

        ProductSeedDto[] productSeedDtos = this.jsonParser.parseFromJson(usersFile, ProductSeedDto[].class);

        Arrays.stream(productSeedDtos).forEach(productSeedDto -> System.out.println(this.productService.saveProduct(productSeedDto)));
    }

    public void seedCategories() throws FileNotFoundException {
        File usersFile = new File(getClass().getClassLoader().getResource("files/categories.json").getFile());

        CategorySeedDto[] categorySeedDtos = this.jsonParser.parseFromJson(usersFile, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos).forEach(categorySeedDto -> System.out.println(this.categoryService.saveCategory(categorySeedDto)));
    }

    public void feelCategoriesProducts(){
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            Category category = this.categoryService.getCategory(random.nextInt(this.categoryService.size() - 1));
            Product product = this.productService.getProduct(random.nextInt(this.productService.size() - 1));

            System.out.println(this.categoryService.addProductToCategory(category, product));
        }

    }

    public void setBuyerAndSellerToProduct(){
        Random random = new Random();

        for (int i = 1; i <= 200; i++) {
            Product product = this.productService.getProduct(i);
            User buyer = this.userService.getUser(random.nextInt(55) + 1);
            User seller = this.userService.getUser(random.nextInt(33) + 20);

            product.setBuyer(buyer);
            product.setSeller(seller);

            this.productService.saveAndFlush(product);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.userAndProducts();
    }

    /**
     * Query 1 - Products In Range
     */
    public void productInRange(){
        this.productService.productsInRange().stream().map(p -> this.jsonParser.toJson(p)).forEach(System.out::println);
    }

    /**
     * Query 2 - Successfully Sold Products
     */
    public void soldProducts(){
        this.userService.usersWithSoldProducts().stream().map(u -> this.jsonParser.toJson(u)).forEach(System.out::println);
    }

    /**
     * Query 3 - Categories By Products Count
     */
    public void categoriesByProductCount(){
        this.categoryService.getCategoriesByProductCount().stream().map(c -> this.jsonParser.toJson(c))
                .forEach(System.out::println);
    }

    /**
     * Query 4 - Users and Products
     */
    public void userAndProducts(){
        System.out.println(this.jsonParser.toJson(this.userService.usersAndProducts()));
    }

}
