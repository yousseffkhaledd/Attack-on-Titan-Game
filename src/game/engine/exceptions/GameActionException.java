package game.engine.exceptions;

public abstract class GameActionException extends Exception {
    public GameActionException(int resourcesProvided) {
        super();
    }
    public GameActionException(){

    }
    public GameActionException(String message) {
        super(message);
    }

}