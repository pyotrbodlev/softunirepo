package springbootdemo.services;

import springbootdemo.domain.entities.Offer;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService {
    void regOffer(Offer offer);

    Offer findById(String id);

    Offer findByBudgetAndType(BigDecimal familyBudget, String familyApartmentType);

    List<Offer> findAll();
}
