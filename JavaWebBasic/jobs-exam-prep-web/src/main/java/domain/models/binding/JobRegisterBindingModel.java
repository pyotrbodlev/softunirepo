package domain.models.binding;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class JobRegisterBindingModel {
    private String sector;
    private String profession;
    private BigDecimal salary;
    private String description;

    @NotNull
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @NotNull
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @NotNull
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
