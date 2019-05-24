package softuni.cardealer.services;

import softuni.cardealer.domain.dtos.SaleRegisterDto;

public interface SaleService {
    String saveSale(SaleRegisterDto dto);
}
