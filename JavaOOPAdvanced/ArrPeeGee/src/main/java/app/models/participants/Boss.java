package app.models.participants;

import app.contracts.Hero;
import app.contracts.Targetable;
import app.models.Config;

public class Boss implements Targetable {

    //    • Name
    //    • Health
    //    • Damage
    //    • Gold
    //    • A way of knowing if the boss is alive.

    private String name;
    private double health;
    private double damage;
    private double gold;
    private boolean isAlive;

    public Boss(String name) {
        this.name = name;
        this.health = Config.BOSS_HEALTH;
        this.damage = Config.BOSS_DAMAGE;
        this.gold = Config.BOSS_GOLD;
        this.isAlive = Config.NEW_HERO_IS_ALIVE;
    }

    @Override
    public String attack(Targetable target) {
        if (!this.isAlive()) {
            return this.getName() + " is dead! Cannot attack.";
        }

        if (!target.isAlive()){
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

    @Override
    public void takeDamage(double damage) {
        this.setHealth(damage);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public double getGold() {
        return this.gold;
    }

    @Override
    public void setHealth(double health) {
        this.health -= health;
    }

    @Override
    public void giveReward(Targetable targetable) {
        targetable.receiveReward(this.gold);
        this.gold = 0;
    }

    @Override
    public void receiveReward(double reward) {
        this.gold = reward;
    }

    @Override
    public void levelUp() {
        this.health = Config.BOSS_HEALTH;
    }

    @Override
    public boolean isAlive() {
        this.isAlive = this.health > 0;
        return this.isAlive;
    }

}
