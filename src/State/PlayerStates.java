package State;

import Visitor.Visitable;
import Visitor.Visitor;
import gameObjects.Player;

public class PlayerStates implements Visitable {
	private State currentState;
	private Player player;
	State regState = new RegularState();
	
	
	public PlayerStates(Player player) {

		currentState = regState;
		this.player = player;
	}

	public void changeLooks()
	{
		currentState.changeLooks(player);
	}
	public void changeSpeed()
	{
		currentState.changeSpeed(player);
	}

	public void accept(Visitor visitor) {
		currentState = visitor.visit(this);
		changeSpeed();
		changeLooks();
	}

}

//package State;
//
//import Visitor.Visitable;
//import Visitor.Visitor;
//import gameObjects.Player;
//
//public class PlayerStates implements Visitable {
//	private State currentState;
//	private Player player;
//	State regState = new RegularState();
//	
//	
//	public PlayerStates(Player player) {
//
//		currentState = regState;
//		this.player = player;
//	}
//
//	public void changeLooks()
//	{
//		currentState.changeLooks(player);
//	}
//	public void changeSpeed()
//	{
//		currentState.changeSpeed(player);
//	}
//	public void changeState() {
//		
//		if (player.health == 100) {
//			currentState = regState;
//			changeSpeed();changeLooks();
//		}
//		else if (player.health <= 25) {
//			currentState = lowState;
//			changeSpeed();changeLooks();
//		}
//		else if (player.health < 1) {
//			currentState = deadState;
//			changeSpeed();changeLooks();
//		}
//		else if (player.hasMega) {
//			currentState = megaState;
//			changeSpeed();changeLooks();
//		}
//		
//	public void accept(Visitor visitor) {
//		currentState = visitor.visit(this);
//		changeSpeed();
//		changeLooks();
//	}
//
//}
