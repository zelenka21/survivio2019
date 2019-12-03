package gameObjects;

import java.awt.Color;
import java.awt.Rectangle;

import Adapter.IHealth;



public class HealthPack extends Item implements IHealth{
	
	
public int health;
	
	public HealthPack(int x, int y, int health){
		super(x, y);
		
		this.health = health;
		setColor(Color.RED);
		
		
	}
	
	@Override
	public void setHealth(int hp) {
		this.health = hp;
		
	}

	@Override
	public int getHealth() {
		return this.health;
	}
	
	

}
