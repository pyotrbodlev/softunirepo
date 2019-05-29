package mostwanted.domain.dtos.xml_import_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacesListXmlDto {

    @XmlElement(name = "race")
    private List<RaceImportDto> races;

    public List<RaceImportDto> getRaces() {
        return races;
    }

    public void setRaces(List<RaceImportDto> races) {
        this.races = races;
    }
}
