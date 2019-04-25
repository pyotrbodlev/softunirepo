package salesdb.app.core;

import salesdb.app.entites.Customer;
import salesdb.app.entites.Product;
import salesdb.app.entites.Sale;
import salesdb.app.entites.StoreLocation;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;

public class Engine implements Runnable {
    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {
        Sale sale = new Sale();

        Product product = new Product("Bira", 10, BigDecimal.valueOf(2.50));
        Customer customer = new Customer("Pesho");
        StoreLocation storeLocation = new StoreLocation("Sofia");

        sale.setCustomer(customer);
        sale.setProduct(product);
        sale.setStoreLocation(storeLocation);
        sale.setDate(new Date());

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(sale);
        this.entityManager.getTransaction().commit();
    }
}
