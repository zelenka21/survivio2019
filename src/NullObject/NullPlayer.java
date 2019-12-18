package NullObject;

import java.awt.Color;

import Decorator.DecoratedPlayer;

public class NullPlayer implements DecoratedPlayer {
	Color col=Color.cyan;

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		col = color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.cyan;
	}

}
