package gameObjects;

import java.awt.Color;
import java.awt.Rectangle;

import Adapter.IHealth;



public class HealthPack extends Item implements IHealth{
	
	
public int health;
	
	public HealthPack(int x, int y, int health){
		super(x, y);
		
		this.health = health;
		this.color = Color.RED;
		
		
	}
	
	@Override
	public Rectangle bounds(){
		return new Rectangle(cPos.x, cPos.y, 7, 7);
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
