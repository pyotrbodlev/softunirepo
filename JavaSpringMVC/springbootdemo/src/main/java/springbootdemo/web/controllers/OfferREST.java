package springbootdemo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springbootdemo.domain.entities.Offer;
import springbootdemo.services.OfferService;

import java.util.List;

@RestController()
public class OfferREST {
    private OfferService offerService;

    @Autowired
    public OfferREST(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/find/{id}")
    public Offer findOfferByIndex(@PathVariable String id) {
        return this.offerService.findById(id);
    }

    @GetMapping("/find-all")
    public List<Offer> offerList(){
        return this.offerService.findAll();
    }
}
