package Adapter;

import java.awt.Color;
import java.awt.Rectangle;

import Adapter.IHealth;
import gameObjects.Item;



public class MegaHealthPack extends Item implements IMegaHealth{
	
	
public int health;
	
	public MegaHealthPack(int x, int y){
		super(x, y);
		
		this.color = Color.orange;
		
		
	}
	
	@Override
	public Rectangle bounds(){
		return new Rectangle(cPos.x, cPos.y, 10, 10); // 7>>>10 size
	}

	@Override
	public void setHealth(int hp) {
		this.health = hp;
		
	}

	@Override
	public void setMegaHealth() {
		this.health = 50;
		
	}
	

}
