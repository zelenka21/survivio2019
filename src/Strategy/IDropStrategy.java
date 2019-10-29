package Strategy;

public interface IDropStrategy {

	int getDropID(int dropID);
	
	static IDropStrategy dropHealth()
	{
		return dropID -> dropID;
	}
	static IDropStrategy dropAmmo()
	{
		return dropID -> dropID;
	}
	
}
