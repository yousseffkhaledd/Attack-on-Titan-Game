package game.gui;

import game.engine.BattlePhase;
import game.engine.lanes.Lane;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private int score = 0;
    private int turn = 1;
    private String mode; // "Easy" or "Hard"
    private List<Lane> lanes;
    private BattlePhase currentPhase;

    public void Game(String mode) {
        this.mode = mode;
        initializeLanes(mode);
    }

    private void initializeLanes(String mode) {
        int numLanes = mode.equals("Easy") ? 3 : 5;
        int initialResources = mode.equals("Easy") ? 250 : 125;
        lanes = new ArrayList<>();
        for (int i = 0; i < numLanes; i++) {

        }
    }

    public void update() {
        // Check game phase or state if your game has multiple phases (e.g., setup, active, paused)
        switch (currentPhase) {
            case EARLY:
                updateLanes();
                checkGameConditions();
                break;
            case INTENSE:
                // Possibly save the game state or just wait for user input
                break;
            case GRUMBLING:
                endGame();
                break;
        }

        // Increment turn counter after each full cycle of updates
        incrementTurn();
    }

    private void updateLanes() {
        // Update each lane, which includes moving titans and activating weapons
        for (Lane lane : lanes) {
            lane.updateLaneDangerLevel();
        }
    }

    private void checkGameConditions() {
        // Check for any end-game conditions or phase transitions
        if (allLanesDefeated()) {
            currentPhase = BattlePhase.GRUMBLING;
        } else {
            updateScoreAndResources();
        }
    }

    private boolean allLanesDefeated() {
        return lanes.stream().allMatch(Lane::isLaneLost);
    }

    private void updateScoreAndResources() {
        // Update score based on current game state, e.g., titans defeated, resources spent
        for (Lane lane : lanes) {
            score += lane.performLaneWeaponsAttacks();
            // Assume each lane can potentially generate or consume resources
        }
    }

    private void endGame() {
        // Handle end-game logic, such as calculating final score, saving results, etc.
        System.out.println("Game Over. Final Score: " + score);
        // Reset or prepare the game for a new round if necessary
    }

    private void incrementTurn() {
        turn++;
        System.out.println("Turn " + turn + " begins.");
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public void setLanes(List<Lane> lanes) {
        this.lanes = lanes;
    }

    public BattlePhase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(BattlePhase currentPhase) {
        this.currentPhase = currentPhase;
    }
    // Getters and setters
}
