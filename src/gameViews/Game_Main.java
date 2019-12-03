package gameViews;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
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
import State.PlayerStates;
import Strategy.DropAmmo;
import Strategy.DropHealth;
import Strategy.IDropStrategy;
import gameObjects.AmmoPack;
import gameObjects.Boundary;
import gameObjects.BreakableBoundary;
import gameObjects.HealthPack;
import gameObjects.Item;
import gameObjects.Map;
import gameObjects.MapLoader;
import gameObjects.Player;
import gameObjects.Projectile;
import networking.Connection;
import util.Util;

public class Game_Main {

	
    public static gameFrame window;
	
	public static Player player;
	
	public volatile static ArrayList<Player> players;
	public volatile static ArrayList<DecoratedPlayer> decPlayers;
	public static int erx=0;
	public static int ery=0;
	public static Map map;
	
	public static int fps;

	
	public static void main(String[] args) {

		Facade fc = new Facade();

        String userN = JOptionPane.showInputDialog(null, "Enter Username: ");
  
		erx = getRandomInt(5, 100);
		ery = getRandomInt(5, 100);

		player = new Player(userN, new Connection());
		players = new ArrayList<Player>();
		players.add(player);
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
			

			if(eplayer.getHealth() <= 0){
				players.remove(i);
				continue;
			}
			
			eplayer.move();
			
			if(eplayer.cPos.x != eplayer.pPos.x || 
			   eplayer.cPos.y != eplayer.pPos.y && 
			   eplayer.username.equals(player.username)){
				
				eplayer.connection.echoPosition();
			}
			
			ArrayList<Projectile> projs = eplayer.liveAmmo;
			
			for(int c = 0; c < projs.size(); c++){
				Projectile proj = projs.get(c);
				
				if(proj.move()) {
					for(Player hitPlayer : players) {
						if(!proj.owner.username.equals(hitPlayer.username)) {
							if(Util.intersects(proj.bounds(), hitPlayer.bounds()) /**&& !hitPlayer.shield.on**/) {
								//player is hit
//								PlayerStates pst = new PlayerStates(hitPlayer);
//								pst.changeState();
//								pst.changeSpeed(hitPlayer);
								hitPlayer.takeDamage(proj.damage);
								eplayer.liveAmmo.remove(c);
								pst.changeState();
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
//											//IDropStrategy dropAmmo = new DropAmmo();
//											//dropAmmo.dropItem(new AmmoPack(eplayer.cPos.x, eplayer.cPos.y, 5), map, eplayer, erx, ery);
									fc.dropAmmo(new AmmoPack(eplayer.cPos.x, eplayer.cPos.y, 5), map, eplayer, erx, ery);
									}
								    else
									{ 	//will drop health
							//				IDropStrategy dropHP = new DropHealth();
										//dropHP.dropItem(new HealthPack(eplayer.cPos.x, eplayer.cPos.y, 5), map, eplayer, erx, ery);
										fc.dropHealth(new HealthPack(eplayer.cPos.x, eplayer.cPos.y, 5), map, eplayer, erx, ery);
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
						pst.changeState();
						map.items.remove(f);
						continue;
					}
					if(item instanceof AmmoPack){
						eplayer.addAmmo(((AmmoPack) item).amount);
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
						pst.changeState();
						continue;
					
				}
			}
		}
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