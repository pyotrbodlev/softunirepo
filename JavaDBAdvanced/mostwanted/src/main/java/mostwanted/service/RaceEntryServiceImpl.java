package mostwanted.service;

import mostwanted.repository.RaceEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final RaceEntryRepository raceEntryRepository;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository) {
        this.raceEntryRepository = raceEntryRepository;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() != 0;
    }

    @Override
    public String readRaceEntriesXmlFile() {
        return null;
    }

    @Override
    public String importRaceEntries() {
        return null;
    }
}
