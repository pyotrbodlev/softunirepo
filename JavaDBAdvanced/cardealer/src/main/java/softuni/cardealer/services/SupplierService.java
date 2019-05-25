package softuni.cardealer.services;

import softuni.cardealer.domain.dtos.ListWithSuppliersDto;
import softuni.cardealer.domain.dtos.SupplierRegisterDto;
import softuni.cardealer.domain.dtos.SupplierXmlDto;
import softuni.cardealer.domain.entites.Supplier;

import java.util.List;

public interface SupplierService {
    String saveSupplier(SupplierRegisterDto dto);

    Supplier getSupplier(Integer id);

    ListWithSuppliersDto getAllSuppliersThatDoNotImportParts();
}
