package service;

import domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final EntityManager entityManager;

    public ProductServiceImpl() {
        this.entityManager =
                Persistence.createEntityManagerFactory("products")
                        .createEntityManager();
    }

    @Override
    public List<Product> getAllProducts() {
        CriteriaQuery<Product> query =
                entityManager.getCriteriaBuilder()
                        .createQuery(Product.class);

        query.from(Product.class);

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public Product findByName(String productName) {
        return this.getAllProducts().stream().filter(p -> p.getName().equals(productName)).findFirst().orElse(null);
    }

    @Override
    public void save(Product product) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(product);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void closeConnection() {
        this.entityManager.close();
    }
}
