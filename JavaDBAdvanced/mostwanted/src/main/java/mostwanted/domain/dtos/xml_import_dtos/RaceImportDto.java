package mostwanted.domain.dtos.xml_import_dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDto {
    @XmlElement(name = "laps")
    private int laps;

    @XmlElement(name = "district-name")
    private String districtName;

    @XmlElement(name = "entry")
    @XmlElementWrapper(name = "entries")
    private List<RaceEntrySimpleDto> entries;

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<RaceEntrySimpleDto> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntrySimpleDto> entries) {
        this.entries = entries;
    }
}
