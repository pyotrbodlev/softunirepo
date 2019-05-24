package softuni.cardealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.cardealer.domain.entites.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
