package softuni.springbootdemotest.repositories;

import softuni.springbootdemotest.domain.Client;

public interface ClientRepositoryCustom {
    Client getClientInfoProcedure(String fullName);
}
