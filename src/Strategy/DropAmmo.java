package Strategy;

import gameObjects.AmmoPack;
import gameObjects.Item;
import gameObjects.Map;
import gameObjects.Player;

public class DropAmmo implements IDropStrategy{

	@Override
	public void dropItem(Item item, Map map, Player eplayer) {
		// TODO Auto-generated method stub
		map.items.add(new AmmoPack(eplayer.cPos.x+25, eplayer.cPos.y-43, 5));

	}
 //soon
}
