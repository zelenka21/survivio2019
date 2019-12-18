package NullObject;

import java.util.ArrayList;

import Adapter.IHealth;
import gameObjects.Boundary;
import gameObjects.Item;

public class NullMap implements IMap{
	public String name;
	public ArrayList<Boundary> boundaries;
	public ArrayList<Item> items;
	public ArrayList<IHealth> hps;
	
	public NullMap(){
		this.name = "Empty Map";
		this.boundaries = new ArrayList<Boundary>();
		this.items = new ArrayList<Item>();
		this.hps = new ArrayList<IHealth>();
	}
	
}

