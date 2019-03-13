package hell.entities.heroes;

import hell.enums.HeroType;

public class Wizard extends BaseHero {
    public Wizard(String name) {
        super(name, HeroType.WIZARD.getStrength(), HeroType.WIZARD.getAgility(), HeroType.WIZARD.getIntelligence(),
                HeroType.WIZARD.getHitPoints(), HeroType.WIZARD.getDamage());
    }
}
