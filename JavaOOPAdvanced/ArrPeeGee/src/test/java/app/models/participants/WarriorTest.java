package app.models.participants;


import app.contracts.Targetable;
import app.models.actions.BossFight;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class WarriorTest {

    private Targetable warrior;

    @org.junit.Before
    public void setUp() throws Exception {
        this.warrior = new Warrior("Pesho");
    }

    @org.junit.Test
    public void attack() throws NoSuchFieldException, IllegalAccessException {
        Boss boss = new Boss("Boss");

        Field health = Boss.class.getDeclaredField("health");
        health.setAccessible(true);
        health.set(boss, 1);

        BossFight bossFight = new BossFight();
        bossFight.executeAction(new ArrayList<>(){{add(boss); add(warrior);}});

        System.out.println(boss.getHealth());
        System.out.println(warrior.getGold());
        System.out.println(this.warrior.attack(boss));
        System.out.println(warrior.getGold());
    }
}