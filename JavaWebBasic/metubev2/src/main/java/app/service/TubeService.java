package app.service;

import app.domain.entities.Tube;
import app.domain.models.TubeRegisterModel;

import java.util.List;

public interface TubeService {
    void upload(TubeRegisterModel tubeRegisterModel);

    List<Tube> getAll();

    Tube findById(String id);
}
