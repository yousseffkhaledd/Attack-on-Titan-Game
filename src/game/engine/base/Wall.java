package game.engine.base;

import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.interfaces.Mobil;

public class Wall implements Attackee
{
    private final int baseHealth;
    private  int currentHealth;
    int resourcesValue = -1;
    public Wall(int baseHealth){
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
    }

    public int getBaseHealth()
    {
        return baseHealth;
    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }

    @Override
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = Math.max(currentHealth, 0);
    }

    @Override
    public int getResourcesValue() {
        return resourcesValue;
    }

}
