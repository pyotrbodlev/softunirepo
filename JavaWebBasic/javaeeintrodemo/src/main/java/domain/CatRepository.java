package domain;

import java.util.ArrayList;
import java.util.List;

public class CatRepository {
    private static List<Cat> cats;

    static {
        cats = new ArrayList<>();
    }

    public static void addCat(Cat cat) {
        cats.add(cat);
    }

    public static List<Cat> getAll() {
        return cats;
    }

    public static Cat findByName(String name) {
        return cats.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }
}
