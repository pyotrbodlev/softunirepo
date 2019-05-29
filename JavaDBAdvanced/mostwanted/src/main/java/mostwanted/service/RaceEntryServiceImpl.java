package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.xml_import_dtos.RaceEntriesListXmlDto;
import mostwanted.domain.dtos.xml_import_dtos.RaceEntryImportDto;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private final RaceEntryRepository raceEntryRepository;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceEntryRepository = raceEntryRepository;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() != 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/race-entries.xml").getFile());
    }

    @Override
    public String importRaceEntries() throws JAXBException {
        RaceEntriesListXmlDto raceEntriesListXmlDto =
                this.xmlParser.parseXml(RaceEntriesListXmlDto.class, getClass().getClassLoader().getResource("files/race-entries.xml").getFile());

        StringBuilder sb = new StringBuilder();

        for (RaceEntryImportDto raceEntryDto : raceEntriesListXmlDto.getRaceEntries()) {
            if (!this.carRepository.existsById(raceEntryDto.getCar())
                    || !this.racerRepository.existsByName(raceEntryDto.getRacerName())) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            RaceEntry raceEntry = this.modelMapper.map(raceEntryDto, RaceEntry.class);
            raceEntry.setCar(this.carRepository.findById(raceEntryDto.getCar()).orElse(null));
            raceEntry.setRacer(this.racerRepository.findByName(raceEntryDto.getRacerName()).orElse(null));
            raceEntry.setRace(null);

            this.raceEntryRepository.saveAndFlush(raceEntry);

            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, raceEntry.getClass().getSimpleName(), raceEntry.getId()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
