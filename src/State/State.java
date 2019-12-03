package State;

import gameObjects.Player;

public interface State {
	void changeSpeed(Player p);
	void changeLooks(Player p);
}
