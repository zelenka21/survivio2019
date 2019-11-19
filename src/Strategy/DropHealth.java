package Strategy;

import gameObjects.AmmoPack;
import gameObjects.HealthPack;
import gameObjects.Item;
import gameObjects.Map;
import gameObjects.Player;

public class DropHealth implements IDropStrategy{

	@Override
	public void dropItem(Item item, Map map, Player eplayer) {
		// TODO Auto-generated method stub
		map.items.add(new HealthPack(eplayer.cPos.x+25, eplayer.cPos.y-43, 5));

	}

}
