package gameObjects;

import java.awt.Color;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import gameViews.Game_Main;

public class AmmoPack extends Item{

public int amount;
	
	public AmmoPack(int x, int y, int amount){
		super(x, y);
		
		this.amount = amount;
		setColor(Color.gray);
		
		
	}
	


}
