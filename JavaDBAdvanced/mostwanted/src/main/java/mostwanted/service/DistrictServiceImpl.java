package mostwanted.service;

import mostwanted.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() != 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return null;
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        return null;
    }
}
