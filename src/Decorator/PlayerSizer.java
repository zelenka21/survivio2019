package Decorator;

import java.awt.Color;

public class PlayerSizer extends Decorator{
	public int specialSize=20;
	public Color fancyColor;
	public PlayerSizer(DecoratedPlayer dplayer) {
		super(dplayer);
	}

	

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.fancyColor;
	}

	public int getDsize() {
		return specialSize;
	}
	public void setSpecialSize(int s)
	{
		this.specialSize = s;
	}
}
