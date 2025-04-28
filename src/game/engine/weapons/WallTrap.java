package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class WallTrap extends Weapon
{
    public static final int WEAPON_CODE = 4;

    public WallTrap(int baseDamage)
    {
        super(baseDamage);
    }


    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int resources = 0;
        ArrayList<Titan> titans = new ArrayList<>();
        while (!(laneTitans.isEmpty())) {
            Titan titan = laneTitans.poll();
            if (titan.getDistance() == 0 && titan != null) {
                int resource = titan.takeDamage(getDamage());
                if (resource > 0) {
                    resources += resource;
                }
            }
            if(!titan.isDefeated()) {
                titans.add(titan);
            }

        }
        laneTitans.addAll(titans);
        return resources;
    }
}

