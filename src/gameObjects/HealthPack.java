package gameObjects;

import java.awt.Color;
import java.awt.Rectangle;



public class HealthPack extends Item{
	
	
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

}
