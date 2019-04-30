import java.util.ArrayList;
import java.util.List;

public class Superhero {
    private String name;
    private List<String> powers;

    public Superhero(String name) {
        this.name = name;
        this.powers = new ArrayList<>();
    }

    public List<String> getPowers() {
        return new ArrayList<>(powers);
    }

    public void addPower(String power){
        this.powers.add(power);
    }
}
