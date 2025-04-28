package game.engine.titans;

import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.interfaces.Mobil;

abstract public class Titan implements Comparable<Titan>, Attacker, Attackee , Mobil
{
    private final int baseHealth;
    private int currentHealth;
    private final int baseDamage;
    private final int heightInMeters;
    private int distanceFromBase;
    private int speed;
    private final int resourcesValue;
    private final int dangerLevel;

    public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase,
                 int speed, int resourcesValue, int dangerLevel) {
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.distanceFromBase = distanceFromBase;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }


    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getDamage() {
        return baseDamage;
    }

    public int getDistance() {
        return distanceFromBase;
    }

    public int getSpeed() {
        return speed;
    }

    public int getResourcesValue() {
        return resourcesValue;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = Math.max(currentHealth, 0);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getHeightInMeters() {
        return heightInMeters;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDistance(int distanceFromBase) {
        this.distanceFromBase = Math.max(distanceFromBase, 0);
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public int compareTo(Titan t) {
        return Integer.compare(this.distanceFromBase, t.distanceFromBase);
    }

}
