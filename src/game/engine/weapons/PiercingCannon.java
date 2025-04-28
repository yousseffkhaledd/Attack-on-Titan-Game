package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.titans.Titan;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PiercingCannon extends Weapon
{
    public static final int WEAPON_CODE = 1;
    public PiercingCannon(int baseDamage)
    {
        super(baseDamage);
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {//######
        int resourcesGained = 0;
        
        ArrayList<Titan> titans=new ArrayList<>();
   	
        for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++) {
            Titan titan = laneTitans.poll();
            if (titan != null) {
                int resources =titan.takeDamage(getDamage());
                if (resources > 0) {
                    resourcesGained += resources;
                }
               if(!titan.isDefeated())
                titans.add(titan);
                
            }
        }
        laneTitans.addAll(titans);
        return resourcesGained;
    }

}
