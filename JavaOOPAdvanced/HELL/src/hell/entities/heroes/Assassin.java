package hell.entities.heroes;

import hell.enums.HeroType;

public class Assassin extends BaseHero {
    public Assassin(String name) {
        super(name, HeroType.ASSASSIN.getStrength(), HeroType.ASSASSIN.getAgility(),
                HeroType.ASSASSIN.getIntelligence(), HeroType.ASSASSIN.getHitPoints(), HeroType.ASSASSIN.getDamage());
    }
}
