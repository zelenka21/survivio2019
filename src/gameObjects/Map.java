package gameObjects;

import java.util.ArrayList;

import Adapter.IHealth;
import gameObjects.Boundary;
import gameObjects.Item;

public class Map {

	public ArrayList<Boundary> boundaries;
	public ArrayList<Item> items;
	public ArrayList<IHealth> hps;
	public String name;
	
	public Map(ArrayList<Boundary> boundaries, ArrayList<Item> items, String name){
		this.boundaries = boundaries;
		this.items = items;
		this.name = name;
		//
		this.hps = new ArrayList<IHealth>();
	}
}
