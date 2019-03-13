package app.models.participants;

import app.models.Config;

public class Necromancer extends BaseHero {
    public Necromancer(String name) {
        super(name, Config.NECROMANCER_BASE_STRENGTH, Config.NECROMANCER_BASE_DEXTERITY, Config.NECROMANCER_BASE_INTELLIGENCE,
                Config.HERO_STARTING_LEVEL, Config.NECROMANCER_BASE_STRENGTH * 10,
                (Config.NECROMANCER_BASE_DEXTERITY * 2) + (Config.NECROMANCER_BASE_INTELLIGENCE * 2) + (Config.NECROMANCER_BASE_STRENGTH * 2),
                Config.HERO_START_GOLD, Config.NEW_HERO_IS_ALIVE);
    }

    @Override
    protected void setLevelUpDamage() {
        super.setDamage((super.getStrength() * 2) + (super.getDexterity() * 2) + (super.getIntelligence() * 2));
    }
}
