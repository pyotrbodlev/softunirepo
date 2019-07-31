package app.services;

import app.domain.entities.Tube;
import app.repository.TubeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private TubeRepository tubeRepository;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository) {
        this.tubeRepository = tubeRepository;
    }

    @Override
    public String getAllTubeNames() {
        return this.tubeRepository.findAll().stream().map(Tube::getTitle).collect(Collectors.joining(", "));
    }

    @Override
    public List<Tube> getAll() {
        return this.tubeRepository.findAll();
    }

    @Override
    public void save(Tube tube) {
        this.tubeRepository.persist(tube);
    }

    @Override
    public Tube findByTitle(String title) {
       return this.tubeRepository.findByTitle(title);
    }
}
