package Chainofresponsibility;

import gameObjects.Player;

public class LocationSave implements KeyActionInterface {
	KeyActionInterface successor;

	public LocationSave(KeyActionInterface s) {
		this.successor = s;
	}

	@Override
	public void doAction(int actionConstant, Player p) {
		if (actionConstant != 1) {
			successor.doAction(actionConstant, p);
		} else {
			if (p.hasTeleport) { // save teleport state
				p.hitSave();
			}
		}

	}

}
