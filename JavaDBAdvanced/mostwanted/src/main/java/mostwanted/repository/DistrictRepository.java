package mostwanted.repository;

import mostwanted.domain.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    Boolean existsByName(String name);

    Optional<District> findByName(String name);
}
