package game.engine.interfaces;

public interface Attacker
{
    public int getDamage();
    public default int attack(Attackee target){
        int damage = getDamage() ;
        return target.takeDamage(damage);
    }
}
