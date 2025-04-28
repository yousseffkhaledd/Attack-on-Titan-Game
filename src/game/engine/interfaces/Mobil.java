package game.engine.interfaces;

public interface Mobil
{
    public int getDistance();
    public void setDistance(int distance);
    public int getSpeed();
    public void setSpeed(int speed);
    public default boolean hasReachedTarget(){
        return getDistance() <= 0;
    }
    public default boolean move() {
        int speed = getSpeed();
        int distance = getDistance();
        if (distance <= 0) {
            return true;
        } else{
            setDistance(distance - speed);//// e7na bnetra7 el distance - speed 3ady 34an eletnen nafs el unit
            return hasReachedTarget();
            }
    }
}
