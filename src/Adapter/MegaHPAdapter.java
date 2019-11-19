package Adapter;


public class MegaHPAdapter implements IHealth{

	private final MegaHealthPack mega;
	
	public MegaHPAdapter(MegaHealthPack hpp) {
		this.mega = hpp;
		mega.health=50;
	}
	@Override
	public void setHealth(int hp) {
		// TODO Auto-generated method stub
//		LazyThreadSafeSingleton ltss = LazyThreadSafeSingleton.getInstance();
//		ltss.log("Mega Health pack dropped");
		mega.setMegaHealth();
		
	}
	@Override
	public int getHealth() {
		return mega.health;
	}


}
