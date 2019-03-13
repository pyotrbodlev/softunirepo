package app.models.participants;


import app.models.Config;

public class Warrior extends BaseHero {
    public Warrior(String name) {
        super(name, Config.WARRIOR_BASE_STRENGTH, Config.WARRIOR_BASE_DEXTERITY, Config.WARRIOR_BASE_INTELLIGENCE,
                Config.HERO_STARTING_LEVEL, Config.WARRIOR_BASE_STRENGTH * 10, (Config.WARRIOR_BASE_STRENGTH * 2) + Config.WARRIOR_BASE_DEXTERITY,
                Config.HERO_START_GOLD, Config.NEW_HERO_IS_ALIVE);
    }


    @Override
    protected void setLevelUpDamage() {
        super.setDamage((super.getStrength() * 2) + super.getDexterity());
    }
}
