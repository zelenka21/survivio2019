package Chainofresponsibility;

import gameObjects.Player;
import gameViews.Game_Main;

public class ReportHPAction implements KeyActionInterface {
	KeyActionInterface successor;

	public ReportHPAction(KeyActionInterface s) {
		this.successor = s;
	}

	@Override
	public void doAction(int actionConstant, Player p) {
		if (actionConstant != 3) {
			successor.doAction(actionConstant, p);
		} else {
			Game_Main.window.connectionTextArea.append("Your current HealtPoints: "+p.health+ "\n");
		}

	}

}
