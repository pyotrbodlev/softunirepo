package springbootdemo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springbootdemo.domain.entities.Offer;
import springbootdemo.services.OfferService;

import java.math.BigDecimal;

@Controller()
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/reg")
    public String registerIndex() {
        return "register.html";
    }

    @PostMapping("/reg")
    public String registerOffer(BigDecimal apartmentRent, String apartmentType, BigDecimal agencyCommission) {
        Offer offer = new Offer();
        offer.setApartmentRent(apartmentRent);
        offer.setApartmentType(apartmentType);
        offer.setAgencyCommission(agencyCommission);

        this.offerService.regOffer(offer);

        return "redirect:/";
    }

    @GetMapping("/find")
    public String findOffer() {
        return "find.html";
    }

    @PostMapping("/find")
    public String find(BigDecimal familyBudget, String familyApartmentType, String familyName) {
        Offer offer = this.offerService.findByBudgetAndType(familyBudget, familyApartmentType);

        return String.format("redirect:/find/%s", offer.getId());
    }
}
