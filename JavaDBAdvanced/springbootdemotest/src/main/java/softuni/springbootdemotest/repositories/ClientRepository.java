package softuni.springbootdemotest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.springbootdemotest.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>, ClientRepositoryCustom {

    @Override
    @Procedure(name = "getClientInfo")
    Client getClientInfoProcedure(String fullName);

    @Query(nativeQuery = true, value = "SELECT udf_client_cards_count(?1) as `cards` FROM clients c WHERE c.full_name = ?1")
    Integer getClientCards(String fullName);
}
