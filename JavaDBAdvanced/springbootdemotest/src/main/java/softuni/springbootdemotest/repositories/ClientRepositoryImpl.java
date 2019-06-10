package softuni.springbootdemotest.repositories;

import softuni.springbootdemotest.domain.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

public class ClientRepositoryImpl implements ClientRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Client getClientInfoProcedure(String clientFullName) {
        StoredProcedureQuery getClientInfo =
                em.createNamedStoredProcedureQuery("getClientInfo").setParameter("full_name", clientFullName);

        return (Client) getClientInfo.getSingleResult();
    }
}
