package Chainofresponsibility;

import gameObjects.Player;
import gameViews.Game_Main;

public class ReportAmmoAction implements KeyActionInterface {
	KeyActionInterface successor;

	public ReportAmmoAction(KeyActionInterface s) {
		this.successor = s;
	}
	public ReportAmmoAction() {
	
	}


	@Override
	public void doAction(int actionConstant, Player p) {
			Game_Main.window.connectionTextArea.append("Your current Ammo : "+p.ammo.amount + "\n");
		

	}

}
