package Chainofresponsibility;

import gameObjects.Player;

public class TeleportAction implements KeyActionInterface {
	KeyActionInterface successor;

	public TeleportAction(KeyActionInterface s) {
		this.successor = s;
	}

	@Override
	public void doAction(int actionConstant, Player p) {
		if (actionConstant != 2) {
			successor.doAction(actionConstant, p);
		} else {
			if (p.hasTeleport) { // save teleport state
				p.hitUndo();
				p.hasTeleport = false;
			}
		}

	}

}
