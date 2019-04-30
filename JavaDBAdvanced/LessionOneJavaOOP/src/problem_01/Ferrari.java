package problem_01;

public class Ferrari implements Car {
    private static final String SPIDER_488_MODEL = "488-Spider";

    private String model;
    private String driver;

    public Ferrari(String driver) {
        this.driver = driver;
        this.model = SPIDER_488_MODEL;
    }

    public String brakes(){
        return "Brakes!";
    }

    public String gasPedal(){
        return "Zadu6avam sA!";
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s", this.model, this.brakes(), this.gasPedal(), this.driver);
    }
}
