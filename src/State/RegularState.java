package State;

import gameObjects.Player;
import java.awt.Color;

public class RegularState implements State{

	@Override
	public void changeSpeed(Player p) {
		p.setSpeed(2);
		
	}

	@Override
	public void changeLooks(Player p) {
		p.specColor = Color.BLACK;
	}

}
