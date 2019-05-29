package mostwanted.domain.dtos.xml_import_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesListXmlDto {

    @XmlElement(name = "race-entry")
    private List<RaceEntryImportDto> raceEntries;

    public List<RaceEntryImportDto> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntryImportDto> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
