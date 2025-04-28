package game.engine.weapons;

import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;

public class WeaponRegistry
{
	private final int code;
	private String name;
	private int price;
	private int damage;
	private int minRange;
	private int maxRange;

	public int getCode() {
		return code;
	}

	public int getPrice() {
		return price;
	}

	public int getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public WeaponRegistry(int code, int price) {
		this.code = code;
		this.price = price;
		
	}

	public WeaponRegistry(int code, int price, int damage, String name) {
		this.code = code;
		this.price = price;
		this.damage = damage;
		this.name = name;

	}

	public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange) {
		this.code = code;
		this.price = price;
		this.damage = damage;
		this.name = name;
		this.minRange = minRange;
		this.maxRange = maxRange;
	}
    public Weapon buildWeapon(){
        switch (code){
            case 1: return new PiercingCannon(getDamage());
            case 2: return new SniperCannon(getDamage());
            case 3: return new VolleySpreadCannon(getDamage(),getMinRange(),getMaxRange());
            case 4: return new WallTrap(getDamage());
            default: return null ;
        }
    }
}

