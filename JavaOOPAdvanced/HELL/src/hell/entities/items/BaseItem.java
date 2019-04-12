package hell.entities.items;

import hell.interfaces.Item;

public abstract class BaseItem implements Item {
    private String name;
    private int strengthBonus;
    private int agilityBonus;
    private int intelligenceBonus;
    private int hitPointsBonus;
    private int damageBonus;

    protected BaseItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.agilityBonus = agilityBonus;
        this.intelligenceBonus = intelligenceBonus;
        this.hitPointsBonus = hitPointsBonus;
        this.damageBonus = damageBonus;
    }

    public String getName() {
        return name;
    }

    public int getStrengthBonus() {
        return strengthBonus;
    }

    public int getAgilityBonus() {
        return agilityBonus;
    }

    public int getIntelligenceBonus() {
        return intelligenceBonus;
    }

    public int getHitPointsBonus() {
        return hitPointsBonus;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    @Override
    public String toString() {
        return String.format("###Item: %s%n###+%s Strength%n###+%s Agility%n###+%s Intelligence%n###+%s HitPoints%n###+%s Damage",
                this.name, this.strengthBonus, this.agilityBonus, this.intelligenceBonus, this.hitPointsBonus, this.damageBonus);

    }
}
