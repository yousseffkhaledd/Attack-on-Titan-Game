package game.engine.lanes;

import game.engine.base.Wall;
import game.engine.weapons.Weapon;
import game.engine.titans.Titan;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lane implements Comparable <Lane>
{
    private final Wall laneWall;
    private int dangerLevel;
    private final PriorityQueue<Titan> titans;
    private final ArrayList<Weapon> weapons;

    public Lane(Wall laneWall) {
        this.laneWall = laneWall;
        dangerLevel = 0;
        this.titans=new PriorityQueue<Titan>();
        this.weapons=new ArrayList<Weapon>();
    }

    public Wall getLaneWall() {
        return laneWall;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public PriorityQueue<Titan> getTitans() {
        return titans;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    @Override
    public int compareTo(Lane o) {
        return this.dangerLevel - o.dangerLevel;
    }
    public void addTitan(Titan titan){
        titans.add(titan);
        //updateLaneDangerLevel();//  ###     34an 23raf eh el danger bta3 el titan el geded w momken  habdaya Private test
    }
    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }
    public void moveLaneTitans(){//####
        ArrayList<Titan> t=new ArrayList<>();
        while(!titans.isEmpty()) {
            Titan titan=titans.poll();
            t.add(titan);
            if(!titan.hasReachedTarget())
                titan.move();
        }
        titans.addAll(t);
    }
    public int performLaneTitansAttacks(){
        int resourcesGathered = 0;
        for (Titan titan : titans) {
            if(titan.hasReachedTarget() && !titan.isDefeated()) {
                resourcesGathered += titan.attack(laneWall);
            }
        }
        return resourcesGathered;
    }
    public int performLaneWeaponsAttacks(){
        int resourcesGathered = 0;
        for (Weapon weapon : weapons) {
            if (!laneWall.isDefeated() && !isLaneLost())
                resourcesGathered += weapon.turnAttack(titans);
        }
        return resourcesGathered;

    }
    public boolean isLaneLost(){
        return laneWall.isDefeated();
    }
    public void updateLaneDangerLevel() {
        int DangerLevel = 0;
        for (Titan titan : titans) {
            DangerLevel += titan.getDangerLevel();
        }
        dangerLevel = DangerLevel;
    }

}
