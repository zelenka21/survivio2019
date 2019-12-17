package Memento;


import java.awt.Point;

import gameObjects.Player;

public class Caretaker {
	
	private Point cPos;
	private TeleportState tpState;
	
	public void hitSave(Player p) {
		tpState = p.save();
	}
	public void hitUndo(Player p) {
		p.restore(tpState);
	}

}


