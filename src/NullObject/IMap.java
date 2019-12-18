package NullObject;

import java.util.ArrayList;

import Adapter.IHealth;
import gameObjects.Boundary;
import gameObjects.Item;

public interface IMap {
	
	 String name ="";
	static ArrayList<Boundary> boundaries = new ArrayList<Boundary>();
	static ArrayList<Item> items = new ArrayList<Item>();
	static ArrayList<IHealth> hps = new ArrayList<IHealth>();

}
