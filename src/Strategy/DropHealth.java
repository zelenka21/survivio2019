package Strategy;

import gameObjects.AmmoPack;
import gameObjects.HealthPack;
import gameObjects.Item;
import gameObjects.Map;
import gameObjects.Player;

public class DropHealth implements IDropStrategy{

	@Override
	public void dropItem(Item item, Map map, Player eplayer, int erx, int ery) {
		// 
		map.items.add(new HealthPack(eplayer.cPos.x+erx, eplayer.cPos.y-ery, 5));

	}

}
