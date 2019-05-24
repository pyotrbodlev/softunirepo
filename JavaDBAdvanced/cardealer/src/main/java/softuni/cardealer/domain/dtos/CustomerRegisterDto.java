package softuni.cardealer.domain.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CustomerRegisterDto {
    private String name;
    private LocalDateTime birthDate;
    private boolean isYoungDriver;

    public CustomerRegisterDto() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
