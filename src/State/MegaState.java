package State;

import gameObjects.Player;
import java.awt.Color;
public class MegaState implements State{

	@Override
	public void changeSpeed(Player p) {
		p.setSpeed(5);
		
	}

	@Override
	public void changeLooks(Player p) {

		p.specColor = Color.YELLOW;
		
	}

}
