package hell.entities.heroes;

import hell.enums.HeroType;

public class Barbarian extends BaseHero {
    public Barbarian(String name) {
        super(name, HeroType.BARBARIAN.getStrength(), HeroType.BARBARIAN.getAgility(), HeroType.BARBARIAN.getIntelligence(),
                HeroType.BARBARIAN.getHitPoints(), HeroType.BARBARIAN.getDamage());
    }
}
