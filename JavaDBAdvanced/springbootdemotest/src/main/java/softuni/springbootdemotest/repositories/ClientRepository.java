package softuni.springbootdemotest.repositories;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import softuni.springbootdemotest.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>, ClientRepositoryCustom {

    @Override
    @Procedure(name = "getClientInfo")
    Client getClientInfoProcedure( String fullName);
}
