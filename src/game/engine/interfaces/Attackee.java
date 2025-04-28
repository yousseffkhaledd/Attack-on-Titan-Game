package game.engine.interfaces;

import game.engine.base.Wall;

public interface  Attackee
{
    public int getCurrentHealth();
    public void setCurrentHealth(int health);

    public int getResourcesValue();
    public default boolean isDefeated() {
       return getCurrentHealth() <= 0 ;
    }
    public default int takeDamage(int damage){
        int currentHealth = getCurrentHealth();
        setCurrentHealth(currentHealth - damage);
        if (isDefeated()) {
            return getResourcesValue();
        }else
             return 0;
    }
}
