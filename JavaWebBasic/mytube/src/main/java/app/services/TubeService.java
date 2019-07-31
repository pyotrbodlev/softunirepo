package app.services;

import app.domain.entities.Tube;

import java.util.List;

public interface TubeService {
    String getAllTubeNames();

    List<Tube> getAll();

    void save(Tube tube);

    Tube findByTitle(String title);
}
