package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.export_dtos.CarExportDto;
import mostwanted.domain.dtos.export_dtos.RacerExportDto;
import mostwanted.domain.dtos.json_import_dtos.RacerImportDto;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class RacerServiceImpl implements RacerService {

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() != 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/racers.json").getFile());
    }

    @Override
    public String importRacers(String racersFileContent) {
        RacerImportDto[] racerImportDtos = this.gson.fromJson(racersFileContent, RacerImportDto[].class);
        StringBuilder sb = new StringBuilder();

        for (RacerImportDto racerImportDto : racerImportDtos) {
            if (!this.validationUtil.isValid(racerImportDto)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            if (this.racerRepository.existsByName(racerImportDto.getName())) {
                sb.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Racer racer = this.modelMapper.map(racerImportDto, Racer.class);
            Town town = this.townRepository.findByName(racerImportDto.getHomeTown()).orElse(null);

            racer.setHomeTown(town);

            this.racerRepository.saveAndFlush(racer);

            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    racer.getClass().getSimpleName(),
                    racer.getName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        return this.racerRepository.findAllOrderByCarsDesc()
                .stream()
                .map(r -> {
            RacerExportDto racerExportDto = this.modelMapper.map(r, RacerExportDto.class);
            racerExportDto.setCars(r.getCars().stream().map(c -> this.modelMapper.map(c, CarExportDto.class)).collect(Collectors.toList()));
            return racerExportDto;
        })
                .map(r -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: ").append(r.getName()).append(System.lineSeparator()).append("Cars:").append(System.lineSeparator());

            for (CarExportDto car : r.getCars()) {
                sb.append(String.format(" %s", car.toString())).append(System.lineSeparator());
            }
            return sb.toString().trim();
        })
                .collect(Collectors.joining(System.lineSeparator()));
    }
}