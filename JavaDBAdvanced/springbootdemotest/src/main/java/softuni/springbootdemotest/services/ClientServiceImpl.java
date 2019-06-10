package softuni.springbootdemotest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.springbootdemotest.domain.Client;
import softuni.springbootdemotest.domain.ClientExportDto;
import softuni.springbootdemotest.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String getClientInfo(String clientFullName){
        Client client = this.clientRepository.getClientInfoProcedure(clientFullName);
        return String.format("%s %d years old. Bank account number: %s.\nClient has $%s on his account",
                client.getFullName(), client.getAge(), client.getBankAccount().getAccountNumber(), client.getBankAccount().getBalance());
    }
}
