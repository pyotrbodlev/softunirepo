package mostwanted.service;

import mostwanted.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() != 0;
    }

    @Override
    public String readTownsJsonFile() {
        return null;
    }

    @Override
    public String importTowns(String townsFileContent) {
        return null;
    }

    @Override
    public String exportRacingTowns() {
        return null;
    }
}
