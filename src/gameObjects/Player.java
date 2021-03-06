package gameObjects;

import java.util.ArrayList;

import Chainofresponsibility.LocationSave;
import Chainofresponsibility.OtherAction;
import Chainofresponsibility.ReportAmmoAction;
import Chainofresponsibility.ReportHPAction;
import Chainofresponsibility.TeleportAction;
import Decorator.DecoratedPlayer;
import Memento.Caretaker;
import Memento.TeleportMemento;
import Memento.TeleportState;
import State.PlayerStates;
import gameViews.Game_Main;
import networking.Connection;
import util.Util;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Point;

import java.awt.event.KeyEvent;


public class Player implements DecoratedPlayer{

	
	
	public String username;
	public Color color;
	public Connection connection;
	public boolean showMiniHUD;
	
	public int health;
	public int totalHealth;
	public Ammo ammo;
	public ArrayList<Projectile> liveAmmo;
	//public Shield shield;
	public int speed;
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public int xVel;
	public int yVel;
	
	public Point cPos;
	public Point pPos;
	public boolean hasMega = false;
	public boolean hasTeleport = false;
	public Color specColor;
	//memento teleport
	private TeleportState myTeleportState;
	
	
	public Player(String username, Connection connection) {
		this.username = username;
		this.color = Color.BLUE;
		this.connection = connection;
		this.showMiniHUD = true;
		
		cPos = new Point(300,300);
		pPos = new Point(300,300);
		
		ammo = new Ammo(new Projectile(this, cPos, pPos), 100);
		liveAmmo = new ArrayList<Projectile>();
		//shield = new Shield(5000, new Color(255, 153, 255));
		health = 50;
		totalHealth = 50;
		speed = 2;
		xVel = 0;
		yVel = 0;
		up = false;
		down = false;
		left = false;
		right = false;
	}
	public Player(String username, Color color,  Connection connection) {
		this.username = username;
		this.color =color;
		this.connection = connection;
		this.showMiniHUD = true;
		
		cPos = new Point(300,300);
		pPos = new Point(300,300);
		
		ammo = new Ammo(new Projectile(this, cPos, pPos), 100);
		liveAmmo = new ArrayList<Projectile>();
		health = 50;
		totalHealth = 50;
		speed = 2;
		xVel = 0;
		yVel = 0;
		up = false;
		down = false;
		left = false;
		right = false;
	}
	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color=color;
	}
	public void setColor(String color) {
		switch (color) {
		case "orange":
			this.color = Color.orange;
			break;
		case "black":
			this.color = Color.black;
			break;
		case "yellow":
			this.color = Color.yellow;
			break;
		default:
		    this.color = Color.gray;
		    break;

		}
	}
	

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public Rectangle bounds() {
		return new Rectangle(cPos.x, cPos.y, 10, 10);
	}
	
	public void takeDamage(int damage) {
		if(health - damage < 0){
			health = 0;
		}else{
			health -= damage;
		}
	}
	
	public void addHealth(int health) {
		if(this.health + health <= this.totalHealth) {
			this.health += health;
		} else {
			this.health = this.totalHealth;
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

	
	public void move() {
		Point p = new Point(cPos.x + xVel, cPos.y + yVel);
		if(p.x < 690 && p.y < 690 && p.x > 0 && p.y > 0  && !Util.inBoundaries(Game_Main.map.boundaries, new Rectangle(p.x, p.y, 10, 10))){
			pPos = cPos;
			cPos = p;
		}
	}
	
	public void stop() {
		up = false;
		down = false;
		left = false;
		right = false;
		updateMovement();
	}
	
	public void shoot(Projectile projectile) {
		if(ammo.amount > 0){
			ammo.amount--;
			liveAmmo.add(projectile);
		}
	}
	
	public void addAmmo(int amount) {
		if(ammo.amount + amount <= ammo.total){
			ammo.amount += amount;
		}else{
			ammo.amount = ammo.total;
		}
	}
	
	private void updateMovement() {
		xVel = 0;
		yVel = 0;
	    if(down) yVel = speed;
	    if(up) yVel = -speed;
	    if(left) xVel = -speed;
	    if(right) xVel = speed;
	}
	
	public void keyPressed(KeyEvent e) {
		
		ReportAmmoAction ra = new ReportAmmoAction();
		ReportHPAction ot = new ReportHPAction(ra);
		TeleportAction tp = new TeleportAction(ot);
		LocationSave loc = new LocationSave(tp);
		
		int actionConst;
		

		//loc.doAction(1, this);
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_S:
				down = true;
				break;
			case KeyEvent.VK_W:
				up = true;
				break;
			case KeyEvent.VK_A:
				left = true;
				break;
			case KeyEvent.VK_D:
				right = true;
				break;
			case KeyEvent.VK_R:
				actionConst=1;
				loc.doAction(actionConst, this);
				break;
			case KeyEvent.VK_T://teleport
				actionConst=2;
				loc.doAction(actionConst, this);
				break;
			case KeyEvent.VK_Y://check hp
				actionConst=3;
				loc.doAction(actionConst, this);
				break;
			case KeyEvent.VK_U://check ammo
				actionConst=4;
				loc.doAction(actionConst, this);
				break;
//			case KeyEvent.VK_R:
//				if(hasTeleport) { // save teleport state
//					hitSave();
//				}
//				break;
//			case KeyEvent.VK_T://teleport
//				if(hasTeleport) {
//					hitUndo();
//					hasTeleport = false;
//				}
//				break;
		}
		updateMovement();
		
		
	}
	
	
	
	
	
//	//memento methods

	public TeleportState save() {
		return new TeleportState(cPos.x, cPos.y);
	}
	public void restore(TeleportState save) {
		cPos.x = save.getX(); 
		cPos.y = save.getY(); 
	}
//	
	//player acts as caretaker for memento	
	public void hitSave() {
		myTeleportState = save();
	}
	public void hitUndo() {
		restore(myTeleportState);
	}
	
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_S:
				down = false;
				break;
			case KeyEvent.VK_W:
				up = false;
				break;
			case KeyEvent.VK_A:
				left = false;
				break;
			case KeyEvent.VK_D:
				right = false;
				break;
		}
		updateMovement();
	}
	
	@Override
	public Color getColor() {
		return this.color;
		
	}
//    public static class Memento {
//        private final Point telePos;
//
//        public Memento(Point cPos) {
//        	telePos = cPos;
//        }
// 
//        // accessible by outer class only
//        private Point getSavedState() {
//            return telePos;
//        }
//    }
//	

	
	
}
