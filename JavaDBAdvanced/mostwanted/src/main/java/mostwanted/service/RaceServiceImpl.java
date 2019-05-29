package mostwanted.service;

import mostwanted.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() != 0;
    }

    @Override
    public String readRacesXmlFile() {
        return null;
    }

    @Override
    public String importRaces() {
        return null;
    }
}
