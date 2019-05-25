package softuni.cardealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.cardealer.domain.entites.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c ORDER BY c.birthDate ASC, c.youngDriver DESC")
    List<Customer> findAllOrderByBirthDate();

    @Query("SELECT c FROM Customer c WHERE c.sales.size >= 1")
    List<Customer> findAllWithAtLeastOneBoughtCar();

}
