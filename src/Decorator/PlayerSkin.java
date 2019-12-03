package Decorator;

import java.awt.Color;

public class PlayerSkin extends Decorator{
	
	public Color fancyColor;
	
	public PlayerSkin(DecoratedPlayer dplayer) {
		super(dplayer);
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
		
	}
	
	public void setFancyColor(String color) {
		switch (color) {
		case "orange":
			this.fancyColor = Color.orange;
			break;
		case "black":
			this.fancyColor = Color.black;
			break;
		case "yellow":
			this.fancyColor = Color.yellow;
			break;
		case "blue":
			this.fancyColor = Color.blue;
			break;
		default:
		    this.fancyColor = Color.gray;
		    break;

		}
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.fancyColor;
	}

	
}
