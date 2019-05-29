package mostwanted.service;

import mostwanted.repository.RacerRepository;
import org.springframework.stereotype.Service;

@Service
public class RacerServiceImpl implements RacerService{

    private final RacerRepository racerRepository;

    public RacerServiceImpl(RacerRepository racerRepository) {
        this.racerRepository = racerRepository;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() != 0;
    }

    @Override
    public String readRacersJsonFile() {
        return null;
    }

    @Override
    public String importRacers(String racersFileContent) {
        return null;
    }

    @Override
    public String exportRacingCars() {
        return null;
    }
}