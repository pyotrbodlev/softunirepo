package app.service;

import app.domain.entities.Tube;
import app.domain.models.TubeRegisterModel;
import app.repositories.TubeRepository;
import app.utils.ModelMapper;

import javax.inject.Inject;
import java.util.List;

public class TubeServiceImpl implements TubeService {
    private TubeRepository tubeRepository;
    private ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void upload(TubeRegisterModel tubeRegisterModel) {
        Tube tube = this.modelMapper.map(tubeRegisterModel, Tube.class);

        this.tubeRepository.persist(tube);
    }

    @Override
    public List<Tube> getAll() {
        return this.tubeRepository.findAll();
    }

    @Override
    public Tube findById(String id) {
        return this.tubeRepository.findById(id).orElse(null);
    }
}
