package app.models.participants;

import app.contracts.Hero;
import app.contracts.Targetable;
import app.models.Config;

public abstract class BaseHero implements Hero {
    private String name;
    private int strength;
    private int dexterity;
    private int intelligence;

    private int level;
    private double health;
    private int damage;

    private double gold;
    private boolean isAlive;

    protected BaseHero(String name, int strength, int dexterity, int intelligence, int level, int health, int damage, double gold, boolean isAlive) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.isAlive = isAlive;
    }

    public String attack(Targetable target) {
        if (!this.isAlive()) {
            return this.getName() + " is dead! Cannot attack.";
        }

        if (!target.isAlive()) {
            return target.getName() + " is dead! Cannot be attacked.";
        }

        target.takeDamage(this.getDamage());

        String result = this.getName() + " attacked!";
        if (!target.isAlive()) {
            this.levelUp();
            target.giveReward(this);
            result += String.format(" %s has been slain by %s.", target.getName(), this.getName());
        }

        return result;
    }

    public double getReward() {
        double currentGold = this.gold;
        this.gold = 0;
        return currentGold;
    }

    public void receiveReward(double reward) {
        this.gold += reward;
    }

    public void takeDamage(double damage) {
        this.setHealth(damage);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health -= health;
    }

    @Override
    public void giveReward(Targetable targetable) {
        targetable.receiveReward(this.gold);
        this.gold = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDamage() {
        return this.damage;
    }

    public double getGold() {
        return this.gold;
    }

    public boolean isAlive() {
        this.isAlive = this.health > 0;
        return this.isAlive;
    }

    public void levelUp() {
        this.setStrength(this.getStrength() * Config.LEVEL_UP_MULTIPLIER);
        this.setDexterity(this.getDexterity() * Config.LEVEL_UP_MULTIPLIER);
        this.setIntelligence(this.getIntelligence() * Config.LEVEL_UP_MULTIPLIER);
        this.setInitialHealth(this.getStrength() * Config.HERO_HEALTH_MULTIPLIER);
        this.setLevelUpDamage();
        this.level += 1;
    }

    protected abstract void setLevelUpDamage();

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    private void setInitialHealth(double health) {
        this.health = health;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("  Name: %s | Class: %s", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("  Health: %.2f | Damage: %.2f", this.getHealth(), this.getDamage()))
                .append(System.lineSeparator())
                .append(String.format("  %d STR | %d DEX | %d INT | %.2f Gold", this.getStrength(), this.getDexterity(), this.getIntelligence(), this.gold));

        return sb.toString();
    }
}
