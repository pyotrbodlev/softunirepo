package panzer.models.vehicles;

import panzer.contracts.Assembler;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.models.miscellaneous.VehicleAssembler;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVehicle implements Vehicle {
    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defense;
    private int hitPoints;
    private Assembler assembler;

    public AbstractVehicle(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
       setModel(model);
       setWeight(weight);
       setPrice(price);
       setAttack(attack);
       setDefense(defense);
       setHitPoints(hitPoints);
       this.assembler = new VehicleAssembler();
    }

    private void setModel(String model) {
        this.model = model;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    protected void setPrice(BigDecimal price) {
        this.price = price;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    protected void setDefense(int defense) {
        this.defense = defense;
    }

    protected void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public double getTotalWeight() {
        return this.assembler.getTotalWeight() + this.weight;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.assembler.getTotalPrice().add(this.price);
    }

    @Override
    public long getTotalAttack() {
        return this.assembler.getTotalAttackModification() + this.attack;
    }

    @Override
    public long getTotalDefense() {
        return this.assembler.getTotalDefenseModification() + this.defense;
    }

    @Override
    public long getTotalHitPoints() {
        return this.assembler.getTotalHitPointModification() + this.hitPoints;
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.assembler.addArsenalPart(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.assembler.addShellPart(shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.assembler.addEndurancePart(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() throws NoSuchFieldException, IllegalAccessException {
        List<Part> parts = new ArrayList<>();

        Field arsenalPartsField = Assembler.class.getDeclaredField("arsenalParts");
        arsenalPartsField.setAccessible(true);
        List<Part> listOfArsenalParts = (List<Part>) arsenalPartsField.get(this.assembler);

        Field shellPartsField = Assembler.class.getDeclaredField("arsenalParts");
        shellPartsField.setAccessible(true);
        List<Part> listOfShellParts = (List<Part>) shellPartsField.get(this.assembler);

        Field endurancePartsField = Assembler.class.getDeclaredField("enduranceParts");
        endurancePartsField.setAccessible(true);
        List<Part> listOfEndurancePartsField = (List<Part>) endurancePartsField.get(this.assembler);

        parts.addAll(listOfArsenalParts);
        parts.addAll(listOfShellParts);
        parts.addAll(listOfEndurancePartsField);

        return parts;
    }

    @Override
    public String getModel() {
        return this.model;
    }
}
