package game.gui;

import game.engine.titans.Titan;
import game.engine.weapons.Weapon;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Lane {
    private SimpleIntegerProperty wallHealth;
    private SimpleIntegerProperty dangerLevel;
    private ObservableList<Titan> activeTitans;
    private ObservableList<Weapon> deployedWeapons;

    public Lane(int wallHealth) {
        this.wallHealth = new SimpleIntegerProperty(wallHealth);
        this.dangerLevel = new SimpleIntegerProperty(0);
        this.activeTitans = FXCollections.observableArrayList();
        this.deployedWeapons = FXCollections.observableArrayList();
    }

    // Getters and setters
    public IntegerProperty wallHealthProperty() {
        return wallHealth;
    }

    public IntegerProperty dangerLevelProperty() {
        return dangerLevel;
    }

    public ObservableList<Titan> getActiveTitans() {
        return activeTitans;
    }

    public ObservableList<Weapon> getDeployedWeapons() {
        return deployedWeapons;
    }

    // Methods to modify titans and weapons
    public void addTitan(Titan titan) {
        activeTitans.add(titan);
    }

    public void addWeapon(Weapon weapon) {
        deployedWeapons.add(weapon);
    }

    // Update methods
    public void updateWallHealth(int damage) {
        wallHealth.set(wallHealth.get() - damage);
    }

    public void updateDangerLevel(int change) {
        dangerLevel.set(dangerLevel.get() + change);
    }
}
