package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.interfaces.Mobil;
import game.engine.titans.Titan;

import java.util.Map;
import java.util.PriorityQueue;

abstract public class Weapon implements Attacker
{
    private final int baseDamage;

    public Weapon(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getDamage() {
        return baseDamage;
    }
    public abstract int turnAttack(PriorityQueue<Titan> laneTitans);


}
