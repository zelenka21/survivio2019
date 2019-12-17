package Chainofresponsibility;

import gameViews.Game_Main;
import gameObjects.Player;

public class OtherAction implements KeyActionInterface {
	KeyActionInterface successor;

	public OtherAction(KeyActionInterface s) {
		this.successor = s;
	}
	public OtherAction() {
	}

	@Override
	public void doAction(int actionConstant, Player p) {
		Game_Main.window.connectionTextArea.append("other action");

	}

}
