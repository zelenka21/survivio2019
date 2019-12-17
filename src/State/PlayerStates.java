package State;

import gameObjects.Player;

public class PlayerStates {
	private State currentState;
	private Player player;
	State regState;
	State deadState;
	State lowState;
	State megaState;
	
	
	public PlayerStates(Player player) {
		
	    regState = new RegularState();
		deadState = new DeadState();
		lowState = new LowState();
		megaState = new MegaState();
		currentState = regState;
		this.player = player;
	}
	public void setState(State s)
	{
		this.currentState = s;
	}
	public void changeLooks()
	{
		currentState.changeLooks(player);
	}
	public void changeSpeed()
	{
		currentState.changeSpeed(player);
	}
	public void changeState() {
		
		if (player.health == 100) {
			currentState = regState;
			changeSpeed();changeLooks();
		}
		else if (player.health <= 25) {
			currentState = lowState;
			changeSpeed();changeLooks();
		}
		else if (player.health < 1) {
			currentState = deadState;
			changeSpeed();changeLooks();
		}
		else if (player.hasMega) {
			currentState = megaState;
			changeSpeed();changeLooks();
		}
		
	}
	
}
