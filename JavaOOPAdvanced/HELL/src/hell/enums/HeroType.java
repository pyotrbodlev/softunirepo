package hell.enums;

public enum HeroType {
    BARBARIAN(90, 25, 10, 350, 150),
    ASSASSIN(25, 100, 15, 150, 300),
    WIZARD(25, 25, 100, 100, 250);

    private int strength;
    private int agility;
    private int intelligence;
    private int hitPoints;
    private int damage;

    HeroType(int strength, int agility, int intelligence, int hitPoints, int damage) {
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getDamage() {
        return damage;
    }
}
