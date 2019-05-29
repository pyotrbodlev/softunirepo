package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.xml_import_dtos.RaceImportDto;
import mostwanted.domain.dtos.xml_import_dtos.RacesListXmlDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final DistrictRepository districtRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, RaceEntryRepository raceEntryRepository, DistrictRepository districtRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.districtRepository = districtRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() != 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/races.xml").getFile());
    }

    @Override
    public String importRaces() throws JAXBException {
        RacesListXmlDto racesListXmlDto = this.xmlParser.parseXml(RacesListXmlDto.class, getClass().getClassLoader().getResource("files/races.xml").getFile());

        StringBuilder sb = new StringBuilder();

        for (RaceImportDto raceDto : racesListXmlDto.getRaces()) {
            Race race = this.modelMapper.map(raceDto, Race.class);

            District district = this.districtRepository.findByName(raceDto.getDistrictName()).orElse(null);

            List<RaceEntry> raceEntries = raceDto.getEntries().stream()
                    .map(re -> {
                        RaceEntry raceEntry = this.raceEntryRepository.findById(re.getId()).orElse(null);
                        if (raceEntry != null){
                            raceEntry.setRace(race);
                            return raceEntry;
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            race.setDistrict(district);
            race.setEntries(raceEntries);

            this.raceRepository.saveAndFlush(race);

            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    race.getClass().getSimpleName(),
                    race.getId()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
