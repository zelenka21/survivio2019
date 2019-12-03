package State;

import gameObjects.Player;
import java.awt.Color;
public class LowState implements State{

	@Override
	public void changeSpeed(Player p) {
		p.setSpeed(1);
		
	}

	@Override
	public void changeLooks(Player p) {

		p.specColor = Color.RED;
		
	}

}
