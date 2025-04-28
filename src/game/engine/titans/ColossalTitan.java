package game.engine.titans;

import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.interfaces.Mobil;

public class ColossalTitan extends Titan implements Mobil , Attacker , Attackee
{
    public static final int TITAN_CODE=4;

    public ColossalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
    }

    public boolean move() {
        if(!hasReachedTarget()) {
            setDistance(getDistance() - getSpeed());
            setSpeed(getSpeed()+1);
        }
        return hasReachedTarget();
    }
}

