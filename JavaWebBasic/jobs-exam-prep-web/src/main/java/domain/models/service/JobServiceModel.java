package domain.models.service;

import domain.entities.Sector;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class JobServiceModel {
    private String id;
    private Sector sector;
    private String profession;
    private BigDecimal salary;
    private String description;
    private String sectorMiniName;

    @NotNull
    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectorMiniName() {
        return this.sector.name().toLowerCase();
    }

}
