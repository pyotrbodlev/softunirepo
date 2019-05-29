package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.json_import_dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() != 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/districts.json").getFile());
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        DistrictImportDto[] districtImportDtos = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);

        StringBuilder sb = new StringBuilder();

        for (DistrictImportDto districtImportDto : districtImportDtos) {
            if (!this.validationUtil.isValid(districtImportDto)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            if (this.districtRepository.existsByName(districtImportDto.getName())) {
                sb.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            District district = this.modelMapper.map(districtImportDto, District.class);
            Town town = this.townRepository.findByName(districtImportDto.getTownName()).orElse(null);

            district.setTown(town);

            this.districtRepository.saveAndFlush(district);

            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "District", district.getName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
