package Decorator;

import java.awt.Color;

public class PlayerSizer extends Decorator{
	public int specialSize=20;
	public Color fancyColor;
	public PlayerSizer(DecoratedPlayer dplayer) {
		super(dplayer);
	}

	@Override
	public void setFancyColor(String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.fancyColor;
	}

	@Override
	public int getDsize() {
		return specialSize;
	}
	public void setSpecialSize(int s)
	{
		this.specialSize = s;
	}
}
