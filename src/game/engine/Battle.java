package game.engine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.Weapon;
import game.engine.weapons.factory.WeaponFactory;

public class Battle {
    private static final int[][] PHASES_APPROACHING_TITANS = {{ 1, 1, 1, 2, 1, 3, 4 },{ 2, 2, 2, 1, 3, 3, 4 },{ 4, 4, 4, 4, 4, 4, 4}};
    private static final int WALL_BASE_HEALTH = 10000;
    private int numberOfTurns;
    private int resourcesGathered;
    private BattlePhase battlePhase;
    private int numberOfTitansPerTurn=1;
    private int score;
    private int titanSpawnDistance;
    private final WeaponFactory weaponFactory;
    private final HashMap<Integer, TitanRegistry> titansArchives = DataLoader.readTitanRegistry();
    private final ArrayList<Titan> approachingTitans;
    private final PriorityQueue<Lane> lanes;
    private final ArrayList<Lane> originalLanes;

    public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane ) throws IOException {
        this.numberOfTurns = numberOfTurns;
        this.battlePhase = BattlePhase.EARLY;
        this.score = score;
        this.titanSpawnDistance = titanSpawnDistance;
        this.resourcesGathered = initialNumOfLanes * initialResourcesPerLane;
        this.weaponFactory = new WeaponFactory();
        this.approachingTitans = new ArrayList<>();
        this.lanes = new PriorityQueue<>();
        this.originalLanes = new ArrayList<>();
        initializeLanes(initialNumOfLanes, initialResourcesPerLane);
        loadTitansArchives();
    }

    private void loadTitansArchives() {

    }

    private void initializeLanes(int numOfLanes, int initialResourcesPerLane) {
        for (int i = 0; i < numOfLanes; i++) {
            Wall wall = new Wall(WALL_BASE_HEALTH);
            Lane lane = new Lane(wall);
            originalLanes.add(lane);
            lanes.add(lane);
        }
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public int getResourcesGathered() {
        return resourcesGathered;
    }

    public BattlePhase getBattlePhase() {
        return battlePhase;
    }

    public int getNumberOfTitansPerTurn() {
        return numberOfTitansPerTurn;
    }

    public int getScore() {
        return score;
    }

    public int getTitanSpawnDistance() {
        return titanSpawnDistance;
    }

    public WeaponFactory getWeaponFactory() {
        return weaponFactory;
    }

    public HashMap<Integer, TitanRegistry> getTitansArchives() {
        return titansArchives;
    }

    public ArrayList<Titan> getApproachingTitans() {
        return approachingTitans;
    }

    public PriorityQueue<Lane> getLanes() {
        return lanes;
    }

    public ArrayList<Lane> getOriginalLanes() {
        return originalLanes;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public void setResourcesGathered(int resourcesGathered) {
        this.resourcesGathered = resourcesGathered;
    }

    public void setBattlePhase(BattlePhase battlePhase) {
        this.battlePhase = battlePhase;
    }

    public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
        this.numberOfTitansPerTurn = numberOfTitansPerTurn;
    }

    public void setTitanSpawnDistance(int titanSpawnDistance) {
        this.titanSpawnDistance = titanSpawnDistance;
    }

    public void setScore(int score) {
        this.score = score;
    }
    private void initializeLanes(int numOfLanes) {
        for (int i = 0; i < numOfLanes; i++) {
            Wall wall1 = new Wall(WALL_BASE_HEALTH);
            Lane lane = new Lane(wall1);
            originalLanes.add(lane);
            lanes.add(lane);
        }
    }
    public void refillApproachingTitans(){
        approachingTitans.clear();
        for (int titanCode : PHASES_APPROACHING_TITANS[battlePhase.ordinal()]) {
            TitanRegistry titanRegistry = titansArchives.get(titanCode);
            Titan titan = titanRegistry.spawnTitan(titanSpawnDistance);
            approachingTitans.add(titan);
        }
    }
    public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException {
        if (lane.isLaneLost() || !lanes.contains(lane) || lane ==  null ) {
            throw new InvalidLaneException("Action done on an invalid lane");
        }
        Weapon weapon = weaponFactory.buyWeapon(resourcesGathered , weaponCode).getWeapon();
        int resource = resourcesGathered - weaponFactory.getWeaponShop().get(weaponCode).getPrice();
        setResourcesGathered(resource);
        lane.addWeapon(weapon);
        passTurn();
    }

    public void passTurn() {
       performTurn();
    }
    private void addTurnTitansToLane(){
        if (approachingTitans.isEmpty()) {
            refillApproachingTitans();
        }
        for (int i = 0; i < numberOfTitansPerTurn; i++) {
            Lane leastDangerousLane = lanes.peek();
           
            if (approachingTitans.isEmpty()) {//####
	            refillApproachingTitans();
	        } 
            if (leastDangerousLane != null) {
                leastDangerousLane.addTitan(approachingTitans.remove(0));
            }
        }
    }
    private void moveTitans(){
        for(Lane lane : lanes){
            lane.moveLaneTitans();
        }
    }
    private int performWeaponsAttacks(){
        int resources = 0;
        for (Lane lane : lanes) {
            if (!lane.isLaneLost())
                resources += lane.performLaneWeaponsAttacks();
        }
        
        resourcesGathered+=resources;
		 score+=resources;
		
        return resources;
    }

    private int performTitansAttacks(){
        int resources = 0;
        ArrayList<Lane> Lanes=new ArrayList<>();
    	
        
    	while(!lanes.isEmpty()) {
   		Lane lane=lanes.poll();
    	     if (!lane.isLaneLost()) {
                int initialWallHealth =WALL_BASE_HEALTH;
                int resourcesGained = lane.performLaneTitansAttacks();
                if (initialWallHealth <= 0) {
                    lanes.remove();
                    resourcesGained += initialWallHealth;
                }
                if(!lane.isLaneLost())
            		Lanes.add(lane);

                
                resources += resourcesGained;
            }
        }
    	for(Lane l:Lanes)
   		lanes.add(l);
    	
        return resources;
    }

    private void updateLanesDangerLevels(){
		 ArrayList<Lane> Lanes=new ArrayList<>(); 
		 while(!lanes.isEmpty()) {
		   		Lane lane=lanes.poll();
		   	  if(!lane.isLaneLost()) {
	                lane.updateLaneDangerLevel();
		   	Lanes.add(lane);}
	      }
		    	for(Lane l:Lanes)
		   		lanes.add(l);
		    
	    }
    private void finalizeTurns(){
        numberOfTurns++;
        if(numberOfTurns < 15 && numberOfTurns >=0){
            battlePhase = battlePhase.valueOf("EARLY");
        }else if (numberOfTurns >= 15 && numberOfTurns < 30 ){
            battlePhase = BattlePhase.valueOf("INTENSE");
        }else
            battlePhase = BattlePhase.valueOf("GRUMBLING");
        if ( numberOfTurns%5 ==0  && numberOfTurns > 30){
            numberOfTitansPerTurn *= 2 ;
        }
    }
    private void performTurn(){
        if (!isGameOver()) {
            moveTitans();
            getBattlePhase();
            performWeaponsAttacks();
            performTitansAttacks();
            addTurnTitansToLane();
            updateLanesDangerLevels();
            finalizeTurns();
        }
    }

    public boolean isGameOver() {
        for (Lane lane : lanes) {
            if (!lane.isLaneLost()) {
                return false;
            }
        }
        return true;
    }

}