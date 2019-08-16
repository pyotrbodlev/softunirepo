package springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootdemo.domain.entities.Offer;
import springbootdemo.repositories.OfferRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void regOffer(Offer offer) {
        this.offerRepository.save(offer);
    }

    @Override
    public Offer findById(String id) {
        return this.offerRepository.findById(id).orElse(null);
    }

    @Override
    public Offer findByBudgetAndType(BigDecimal familyBudget, String familyApartmentType) {
        return this.offerRepository.findByAgencyCommissionAndApartmentType(familyBudget, familyApartmentType).orElse(null);
    }

    @Override
    public List<Offer> findAll() {
        return this.offerRepository.findAll();
    }
}
