package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.entities.miscellaneous.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class BaseHero implements Hero {
    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private int hitPoints;
    private int damage;
    private Inventory heroInventory;

    protected BaseHero(String name, int strength, int agility, int intelligence, int hitPoints, int damage) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.heroInventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        long result = this.heroInventory.getTotalStrengthBonus() + this.strength;
        return result;
    }

    @Override
    public long getAgility() {
        long result = this.heroInventory.getTotalAgilityBonus() + this.agility;
        return result;
    }

    @Override
    public long getIntelligence() {
        long result = this.heroInventory.getTotalIntelligenceBonus() + this.intelligence;
        return result;
    }

    @Override
    public long getHitPoints() {
        long result = this.heroInventory.getTotalHitPointsBonus() + this.hitPoints;
        return result;
    }

    @Override
    public long getDamage() {
        long result = this.heroInventory.getTotalDamageBonus() + this.damage;
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Item> getItems() throws IllegalAccessException {
        Collection<Item> items = null;

        Field[] declaredFields = HeroInventory.class.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(ItemCollection.class)) {
                Map<String, Item> itemMap = (Map<String, Item>) declaredField.get(this.heroInventory);
                items = itemMap.values();
            }
        }

        return items;
    }

    @Override
    public void addItem(Item item) {
        this.heroInventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.heroInventory.addRecipeItem(recipe);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s: %s", this.getClass().getSimpleName(), this.getName())).append(System.lineSeparator())
                .append(String.format("###HitPoints: %d", this.getHitPoints())).append(System.lineSeparator())
                .append(String.format("###Damage: %d", this.getDamage())).append(System.lineSeparator())
                .append(String.format("###Strength: %d", this.getStrength())).append(System.lineSeparator())
                .append(String.format("###Agility: %d", this.getAgility())).append(System.lineSeparator())
                .append(String.format("###Intelligence: %d", this.getIntelligence())).append(System.lineSeparator())
                .append("###Items:");

        try {
            if (this.getItems().isEmpty()) {
                sb.append(" None");
            } else {
                List<String> items = new ArrayList<>();
                for (Item item : this.getItems()) {
                    items.add(item.getName());
                }

                sb.append(" ").append(items.toString().replaceAll("[\\[\\]]", ""));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
