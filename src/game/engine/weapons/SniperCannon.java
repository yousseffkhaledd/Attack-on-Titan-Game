package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SniperCannon extends Weapon {
    public static final int WEAPON_CODE = 2;

    public SniperCannon(int baseDamage) {
        super(baseDamage);
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int resources = 0;
        ArrayList<Titan> titans = new ArrayList<>();
        Titan titan = laneTitans.poll();
        if (titan != null) {
            int resource = attack(titan);
            if (resource > 0) {
                resources += resource;
            }
            if (!titan.isDefeated())
                laneTitans.add(titan);
        }
        return resources;
    }
}

