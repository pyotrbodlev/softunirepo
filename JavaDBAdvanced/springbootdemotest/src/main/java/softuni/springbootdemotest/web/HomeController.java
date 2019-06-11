package softuni.springbootdemotest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import softuni.springbootdemotest.services.ClientService;

@Controller
public class HomeController implements CommandLineRunner {
    private ClientService clientService;

    @Autowired
    public HomeController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.clientService.getClientCardInfo("Baxy David"));
    }
}
