package Memento;

//memento method
public class TeleportState {

	private int x;
	private int y;
	
	public TeleportState(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
