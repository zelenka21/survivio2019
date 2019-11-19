package Strategy;

import gameObjects.Item;
import gameObjects.Map;
import gameObjects.Player;

public interface IDropStrategy {

	public void dropItem(Item item, Map map, Player eplayer);
	
}
