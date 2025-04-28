package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class VolleySpreadCannon extends Weapon
{
    public static final int WEAPON_CODE = 3;
    private final int minRange;
    private final int maxRange;

    public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
        super(baseDamage);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int resources = 0;
        ArrayList<Titan> titans = new ArrayList<>();
        while (!(laneTitans.isEmpty())) {
            Titan titan = laneTitans.poll();
            if (titan.getDistance() >= minRange && titan.getDistance() <= maxRange) {
                int resource = attack(titan);
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
