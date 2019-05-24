package softuni.cardealer.services;

import softuni.cardealer.domain.dtos.SupplierRegisterDto;
import softuni.cardealer.domain.entites.Supplier;

public interface SupplierService {
    String saveSupplier(SupplierRegisterDto dto);

    Supplier getSupplier(Integer id);
}
