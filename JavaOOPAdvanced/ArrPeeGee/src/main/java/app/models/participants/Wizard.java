package app.models.participants;

import app.models.Config;

public class Wizard extends BaseHero {
    public Wizard(String name) {
        super(name, Config.WIZARD_BASE_STRENGTH, Config.WIZARD_BASE_DEXTERITY, Config.WIZARD_BASE_INTELLIGENCE,
                Config.HERO_STARTING_LEVEL, Config.WIZARD_BASE_STRENGTH * 10, (Config.WIZARD_BASE_INTELLIGENCE * 5) + Config.WIZARD_BASE_DEXTERITY,
                Config.HERO_START_GOLD, Config.NEW_HERO_IS_ALIVE);
    }

    @Override
    protected void setLevelUpDamage() {
        super.setDamage((super.getIntelligence() * 5) + super.getDexterity());
    }
}
