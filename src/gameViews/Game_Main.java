package gameViews;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Adapter.IHealth;
import Adapter.MegaHPAdapter;
import Adapter.MegaHealthPack;
import Decorator.DecoratedPlayer;
import Decorator.PlayerShape;
import Decorator.PlayerSizer;
import Decorator.PlayerSkin;
import Facade.Facade;
import Flyweight.HealthPackFactory;
import NullObject.IMap;
import NullObject.NullMap;
import State.PlayerStates;
import Strategy.DropAmmo;
import Strategy.DropHealth;
import Strategy.IDropStrategy;
import Visitor.DeadVisitor;
import Visitor.LowVisitor;
import Visitor.MegaVisitor;
import Visitor.RegularVisitor;
import gameObjects.*;
import networking.Connection;
import util.Util;

public class Game_Main {

	public static LazyThreadSafeSingleton ltss = LazyThreadSafeSingleton.getInstance();
    public static gameFrame window;
	
	public static Player player;
	
	public volatile static ArrayList<Player> players;
	public volatile static ArrayList<DecoratedPlayer> decPlayers;
	public static int erx=0;
	public static int ery=0;
	public static Map map;
	public static int playerCount=0;

	public static IMap _map;
	
	public static int fps;

	private static final int healthPackValues[] = { 5, 6, 7, 8, 9, 10};
	public static void main(String[] args) {



		Facade fc = new Facade();
		String userN = JOptionPane.showInputDialog(null, "Enter Username: ");
  
		erx = getRandomInt(5, 100);
		ery = getRandomInt(5, 100);

		player = new Player(userN, new Connection());
		players = new ArrayList<Player>();
		players.add(player);
		playerCount++;
		//
		loadMap();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new gameFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			TimeUnit.SECONDS.sleep(2);
	    } catch (InterruptedException e) {}
		gameLoop(fc);
		
	}

	private static void loadMap() {
		File mapFile = null;
		
		JFileChooser mapChooser = new JFileChooser("./src/maps");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".map files", "map");
		mapChooser.setFileFilter(filter);
		if(mapChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			mapFile = mapChooser.getSelectedFile();
		}
		 
		if(mapFile == null) {
			//_map = new NullMap();
			map = new Map(new ArrayList<Boundary>(), new ArrayList<Item>(), "null");
		} else {
			map = MapLoader.load(mapFile.toPath());
		}
	}

	
private static void tick(Facade fc) {
	
		

		for(int i = 0; i < players.size(); i++) {

			Player eplayer = players.get(i);
			
			//player state object
			PlayerStates pst = new PlayerStates(eplayer);
			DeadVisitor deadV = new DeadVisitor();
			LowVisitor lowV = new LowVisitor();
			MegaVisitor megaV = new MegaVisitor();
			RegularVisitor regV = new RegularVisitor();
			

			if(eplayer.getHealth() <= 0){
				players.remove(i);
				 Game_Main.window.connectionTextArea.append("--------GAME OVER-------------\n");
				 Game_Main.window.connectionTextArea.append(eplayer.username + " LOST ");
				continue;
			}
			
			eplayer.move();
			
//			if(eplayer.cPos.x != eplayer.pPos.x || 
//			   eplayer.cPos.y != eplayer.pPos.y && 
//			   eplayer.username.equals(player.username)){
//				
//				eplayer.connection.echoPosition();
//			}
			//echo constantly
			if( eplayer.username.equals(player.username))
				eplayer.connection.echoPosition();
			
			ArrayList<Projectile> projs = eplayer.liveAmmo;
			
			for(int c = 0; c < projs.size(); c++){
				Projectile proj = projs.get(c);
				
				if(proj.move()) {
					for(Player hitPlayer : players) {
						if(!proj.owner.username.equals(hitPlayer.username)) {
							if(Util.intersects(proj.bounds(), hitPlayer.bounds()) /**&& !hitPlayer.shield.on**/) {
								hitPlayer.takeDamage(proj.damage);
								eplayer.liveAmmo.remove(c);
								if(eplayer.getHealth() < 25){
									if(eplayer.getHealth() < 1) {
										pst.accept(deadV);

									}
									else
										pst.accept(lowV);
								}
								break;
							}
						}
					}	
				} else {
					for(int p = 0; p < map.boundaries.size(); p++) {
						Boundary boundary = map.boundaries.get(p);
						if(boundary instanceof BreakableBoundary) {
							BreakableBoundary bound = (BreakableBoundary) boundary;
							if(Util.intersects(new Rectangle(proj.nPos.x, proj.nPos.y, proj.sizeX, proj.sizeY), bound)) {
								fc.boundaryTakeDamage(bound, proj.damage);
								if(fc.boundaryGetHealth(bound) <= 1) {
									fc.boundaryDestroy(bound);
									//map.items.add(new Item(eplayer.cPos.x+25, eplayer.cPos.y-43));
				//------------------------	--------------------------------------  ---------------------------------								
									//dropStrategy
			    //------------------------	--------------------------------------  ---------------------------------					
									erx = getRandomInt(5, 80);
									ery = getRandomInt(5, 80);
									int strategyChooser = (Math.random() <= 0.5) ? 1 : 2;
									
									
								    if(strategyChooser == 1)
									{	//will drop Ammo
									fc.dropAmmo(new AmmoPack(eplayer.cPos.x, eplayer.cPos.y, 5), map, eplayer, erx, ery);
									}
								    else
									{ 	//will drop health
										fc.dropHealth((HealthPack) HealthPackFactory.getHealthPack(eplayer.cPos.x, eplayer.cPos.y, getRandomHealth()), map, eplayer, erx, ery);
								}
									
				//------------------------	--------------------------------------  ---------------------------------								
									//Adapter for MegaHealth drops
			    //------------------------	--------------------------------------  ---------------------------------					
									
									map.hps.add(new MegaHPAdapter(new MegaHealthPack(370, 150)));
								}
								
								if(bound.getHealth() <= 0) {
									map.boundaries.remove(p);
								}
							}
						}
					}
				eplayer.liveAmmo.remove(c);
				}
			}
			
			for(int f = 0 ; f < map.items.size(); f++){
				Item item = map.items.get(f);
				
				if(Util.intersects(eplayer.bounds(), item.bounds())){
					if(item instanceof HealthPack){
						eplayer.addHealth(((HealthPack) item).health);
						if(eplayer.getHealth() < 25)
							pst.accept(lowV);
						else
							pst.accept(regV);
						map.items.remove(f);
						continue;
					}
					if(item instanceof AmmoPack){
						eplayer.addAmmo(((AmmoPack) item).amount);
						map.items.remove(f);
						continue;
					}
					if(item instanceof Teleport){
						eplayer.hasTeleport = true;
						map.items.remove(f);
						continue;
					}
				}
			}
			
			// Mega health pick ups
			for(int f = 0 ; f < map.hps.size(); f++){
				IHealth hpItem = map.hps.get(f);
				
				//if(eplayer.cPos.x == 370 && eplayer.cPos.y == 150 ){
				if(eplayer.cPos.x > 365 && eplayer.cPos.x < 375 && eplayer.cPos.y > 145 && eplayer.cPos.y < 165){
						eplayer.addHealth(hpItem.getHealth());
						eplayer.hasMega = true;
						map.hps.remove(f);
						pst.accept(megaV);
						continue;
					
				}
			}
		}
	}

	private static int getRandomHealth() {
		return healthPackValues[(int)(Math.random()*healthPackValues.length)];
	}
	
	private static void paint() {
		try {
			//window.gameView.paintImmediately(0, 0, 700, 700);
			window.gameView.repaint();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void gameLoop(Facade fc) {
		
		long lastLoopTime = System.nanoTime();
		int targetFPS = 60;
		long optimalTime = 1000000000 / targetFPS;   
		int lastFpsTime = 0;

		   while (true) {
			     
			      long now = System.nanoTime();
			      long updateLength = now - lastLoopTime;
			      lastLoopTime = now;
			      
			      
			      double timesPerFrame = updateLength / ((double)optimalTime);
	
			      lastFpsTime += updateLength;
			      fps++;
			      
			      if (lastFpsTime >= 1000000000) {
			    	 GameView.fps = fps;
			         lastFpsTime = 0;
			         fps = 0;
			      }
			      
			      tick(fc);
			      
			      paint();

			      try {
			    	  Thread.sleep( Math.abs((lastLoopTime-System.nanoTime() + optimalTime)/1000000));
			      } catch(Exception e) {
			    	  e.printStackTrace();
			      }
			}
	}
	private static int getRandomInt(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
	
	
}