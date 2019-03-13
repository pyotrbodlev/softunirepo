package hell.manager;

import hell.constants.ResultConstants;
import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Manager;
import hell.interfaces.Recipe;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramManager implements Manager {
    private static final String HEROES_PATH = "hell.entities.heroes.";

    private Map<String, Hero> registeredHeroes;

    public ProgramManager() {
        this.registeredHeroes = new LinkedHashMap<>();
    }

    @Override
    public String heroCommand(List<String> args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String name = args.get(1);
        String type = args.get(2);

        Hero hero = (Hero) Class.forName(HEROES_PATH + type).getDeclaredConstructor(String.class).newInstance(name);

        this.registeredHeroes.put(name, hero);

        return String.format(ResultConstants.HERO_ADDED, type, name);
    }

    @Override
    public String itemCommand(List<String> args) {
        String itemName = args.get(1);
        String heroName = args.get(2);
        int strBonus = Integer.parseInt(args.get(3));
        int aglBonus = Integer.parseInt(args.get(4));
        int intBonus = Integer.parseInt(args.get(5));
        int hitBonus = Integer.parseInt(args.get(6));
        int dmgBonus = Integer.parseInt(args.get(7));
        Item item = new CommonItem(itemName, strBonus, aglBonus, intBonus, hitBonus, dmgBonus);

        try {
            Hero currentHero = registeredHeroes.get(heroName);
            currentHero.addItem(item);
        } catch (NullPointerException npe){
            return "";
        }

        return String.format(ResultConstants.ITEM_ADDED, itemName, heroName);
    }

    @Override
    public String recipeCommand(List<String> args){
        String itemName = args.get(1);
        String heroName = args.get(2);
        int strBonus = Integer.parseInt(args.get(3));
        int aglBonus = Integer.parseInt(args.get(4));
        int intBonus = Integer.parseInt(args.get(5));
        int hitBonus = Integer.parseInt(args.get(6));
        int dmgBonus = Integer.parseInt(args.get(7));

        List<String> requiredItems = args.subList(8, args.size());

        Recipe recipe = new RecipeItem(itemName, strBonus, aglBonus, intBonus, hitBonus, dmgBonus, requiredItems);

        try {
            Hero currentHero = registeredHeroes.get(heroName);
            currentHero.addRecipe(recipe);
        } catch (NullPointerException npe){
            return "";
        }

        return String.format(ResultConstants.RECIPE_ADDED, itemName, heroName);
    }

    @Override
    public String inspectCommand(List<String> args) throws IllegalAccessException {
        String heroName = args.get(1);

        Hero hero = this.registeredHeroes.get(heroName);

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Hero: %s, Class: %s", hero.getName(), hero.getClass().getSimpleName())).append(System.lineSeparator())
                .append(String.format("HitPoints: %d, Damage: %d", hero.getHitPoints(), hero.getDamage())).append(System.lineSeparator())
                .append(String.format("Strength: %d%nAgility: %d%nIntelligence: %d", hero.getStrength(), hero.getAgility(), hero.getIntelligence()))
                .append(System.lineSeparator())
                .append("Items:");

        if (hero.getItems().isEmpty()) {
            sb.append(" None");
        } else {
            for (Item item : hero.getItems()) {
                sb.append(System.lineSeparator());
                sb.append(item.toString());
            }
        }

        return sb.toString();
    }

    @Override
    public String quitCommand(List<String> args){
        List<Hero> collect = this.registeredHeroes.values().stream().sorted((h1, h2) -> {
            long hero1stats = h1.getStrength() + h1.getAgility() + h1.getIntelligence();
            long hero2stats = h2.getStrength() + h2.getAgility() + h2.getIntelligence();

            if (hero1stats == hero2stats) {
                return Long.compare(h2.getHitPoints() + h2.getDamage(), h1.getHitPoints() + h1.getDamage());
            } else {
                return Long.compare(hero2stats, hero1stats);
            }
        }).collect(Collectors.toList());

        int index = 1;
        StringBuilder sb = new StringBuilder();
        for (Hero hero : collect) {
            sb.append(index++).append(". ").append(hero.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }

}
