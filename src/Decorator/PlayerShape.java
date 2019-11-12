package Decorator;

import java.awt.Color;

public class PlayerShape extends Decorator{
	public int specialShape=1; //1 for round
	public Color fancyColor;
	public PlayerShape(DecoratedPlayer dplayer) {
		super(dplayer);
	}

	@Override
	public void setFancyColor(String color) {
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.fancyColor;
	}

	@Override
	public int getDsize() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setSpecialShape(int s)
	{
		this.specialShape = s;
	}
	public int getSpecialShape()
	{
		return this.specialShape;
	}

}
