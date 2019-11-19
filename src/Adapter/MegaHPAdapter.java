package Adapter;


public class MegaHPAdapter implements IHealth{

	private final MegaHealthPack mega;
	
	public MegaHPAdapter(MegaHealthPack hpp) {
		this.mega = hpp;
		mega.health=50;
	}
	@Override
	public void setHealth(int hp) {
		mega.setMegaHealth();
		
	}
	@Override
	public int getHealth() {
		return mega.health;
	}


}
