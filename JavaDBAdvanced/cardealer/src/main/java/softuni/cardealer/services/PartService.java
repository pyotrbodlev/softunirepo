package softuni.cardealer.services;

import softuni.cardealer.domain.dtos.PartRegisterDto;
import softuni.cardealer.domain.entites.Part;

public interface PartService {
    String savePart(PartRegisterDto dto);

    Part getPart(Integer id);

    int getPartsCount();
}
