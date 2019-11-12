package Decorator;

import java.awt.Color;

abstract class Decorator implements DecoratedPlayer{
	private final DecoratedPlayer dplayer;
	
	public Decorator(DecoratedPlayer dplayer) {
		this.dplayer = dplayer;
	}
	@Override
	public void setColor(Color color)
	{
		dplayer.setColor(color);
		
	}
}
