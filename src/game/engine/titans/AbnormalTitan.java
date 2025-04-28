package game.engine.titans;

import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.interfaces.Mobil;

public class AbnormalTitan extends Titan implements Attacker , Mobil , Attackee
{
    public static final int TITAN_CODE=2;

    public AbnormalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
    }
    public int attack(Attackee target)
    {
        int resourcesGained = super.attack(target);
        if (!target.isDefeated()) {
            resourcesGained +=super.attack(target);
        }
        return resourcesGained;

        }


    }


