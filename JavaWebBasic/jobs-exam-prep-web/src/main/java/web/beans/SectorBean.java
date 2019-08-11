package web.beans;

import domain.entities.Sector;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class SectorBean {

    public SectorBean() {
    }

    public List<String> sectors() {
        List<String> collect = Arrays.stream(Sector.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        return collect;
    }
}
