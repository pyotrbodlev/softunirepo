package alararestaurant.service;

import alararestaurant.common.Texts;
import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.dtos.PositionImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/employees.json").getFile());
    }

    @Override
    public String importEmployees(String employees) {
        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

        StringBuilder sb = new StringBuilder();

        for (EmployeeImportDto importDto : employeeImportDtos) {
            PositionImportDto positionImportDto = new PositionImportDto();
            positionImportDto.setName(importDto.getPosition());

            if (this.validationUtil.isValid(importDto)
                    && this.validationUtil.isValid(positionImportDto)) {
                Employee employee = this.modelMapper.map(importDto, Employee.class);

                Position position = this.positionRepository.findByName(positionImportDto.getName()).orElse(null);

                if (position == null) {
                    position = this.modelMapper.map(positionImportDto, Position.class);

                    this.positionRepository.saveAndFlush(position);
                }

                employee.setPosition(position);

                this.employeeRepository.saveAndFlush(employee);

                sb.append(String.format(Texts.RECORD_IMPORTED, employee.getName())).append(System.lineSeparator());
            } else {
                sb.append(Texts.INVALID_DATA).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
