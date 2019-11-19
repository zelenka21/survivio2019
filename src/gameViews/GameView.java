package gameViews;

import javax.swing.JPanel;

import Decorator.DecoratedPlayer;
import Decorator.PlayerShape;
import Decorator.PlayerSizer;
import Decorator.PlayerSkin;
import gameObjects.Boundary;
import gameObjects.Item;
import gameObjects.Player;
import gameObjects.Projectile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;

public class GameView extends JPanel {


	private static final long serialVersionUID = 1L;
	public static int fps = 0;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		drawBackground(g2);

		drawBoundaries(g2);

		drawItems(g2);

		drawPlayers(g2);
		
		drawDecoPlayers(g2);

		drawProjectiles(g2);

		drawHUD(g2);

		drawMiniHUD(g2);

	}

	private void drawItems(Graphics2D g2) {

		for (Item item : Game_Main.map.items) {
			g2.setColor(item.color);
			
			
			
			g2.fillOval(item.cPos.x, item.cPos.y, 5, 5);
			g2.drawOval(item.cPos.x, item.cPos.y, 5, 5);
			//g2.drawImage(item.image, item.cPos.x, item.cPos.y, this);
		}

	}

	public void drawMiniHUD(Graphics2D g2) {
		for (Player player : Game_Main.players) {
			if (player.showMiniHUD) {
				g2.setColor(new Color(77, 255, 136, 0));
				g2.fillRect(player.cPos.x - 50, player.cPos.y - 40, 110, 35);

				int p_health = 100 * player.health / player.totalHealth;

				int p_ammo = 100 * player.ammo.amount / player.ammo.total;

				// Health
				g2.setColor(new Color(255, 0, 0, 127));
				g2.fillRect(player.cPos.x - 50 + 5, player.cPos.y - 40 + 5, p_health, 10);
				g2.setColor(Color.WHITE);
				g2.drawRect(player.cPos.x - 50 + 5, player.cPos.y - 40 + 5, 100, 10);

				// Ammo
				g2.setColor(new Color(255, 255, 0, 127));
				g2.fillRect(player.cPos.x - 50 + 5, player.cPos.y - 40 + 15, p_ammo, 10);
				g2.setColor(Color.WHITE);
				g2.drawRect(player.cPos.x - 50 + 5, player.cPos.y - 40 + 15, 100, 10);

				g2.drawString(player.username, player.cPos.x - 50 + 5, player.cPos.y - 40);
				
			
			}
		}
	}

	public void drawBoundaries(Graphics2D g2) {

		for (Boundary boundary : Game_Main.map.boundaries) {
			g2.setColor(boundary.color);
			g2.fill(boundary);
		}

	}

	public void drawBackground(Graphics2D g2) {
		g2.setColor(new Color(77, 255, 136));
		g2.fillRect(0, 0, 700, 700);
	}

	public void drawHUD(Graphics2D g2) {

		// HUD
		g2.setColor(new Color(125, 26, 0, 127));
		g2.fillRect(5, 5, 690, 50);

		int p_health = 100 * Game_Main.player.health / Game_Main.player.totalHealth;

		int p_ammo = 100 * Game_Main.player.ammo.amount / Game_Main.player.ammo.total;

		// Health
		g2.setColor(new Color(255, 0, 0, 127));
		g2.fillRect(50, 10, p_health, 10);
		g2.setColor(Color.WHITE);
		g2.drawRect(50, 10, 100, 10);
		g2.drawString("Health: ", 10, 20);

		// Ammo
		g2.setColor(new Color(255, 255, 0, 127));
		g2.fillRect(50, 30, p_ammo, 10);
		g2.setColor(Color.WHITE);
		g2.drawRect(50, 30, 100, 10);
		g2.drawString("Ammo: ", 10, 40);

		// FPS
		g2.drawString("FPS: " + fps, 640, 20);
		g2.drawString("Map: " + Game_Main.map.name, 620, 40);

	}

	public void drawPlayers(Graphics2D g2) {
		for (Player player : Game_Main.players) {

//			if (player.shield.on) {
//				g2.setColor(player.shield.color);
//				g2.fillOval((player.cPos.x + 5) - player.shield.delta, (player.cPos.y + 5) - player.shield.delta,
//						player.shield.delta * 2, player.shield.delta * 2);
//			}

			g2.setColor(player.color);
			g2.fillRect(player.cPos.x, player.cPos.y, 10, 10);
			g2.setColor(Color.BLACK);
			g2.drawRect(player.cPos.x, player.cPos.y, 10, 10);

		}
	}
	public void drawDecoPlayers(Graphics2D g2) {
		//for (DecoratedPlayer player : Game_Main.decPlayers) {

//			if (player.shield.on) {
//				g2.setColor(player.shield.color);
//				g2.fillOval((player.cPos.x + 5) - player.shield.delta, (player.cPos.y + 5) - player.shield.delta,
//						player.shield.delta * 2, player.shield.delta * 2);
//			}

		//	player.setFancyColor("yellow");
			//g2.setColor(player.getColor());
		//	g2.setColor(player.getColor());
			
		//	g2.fillRect(400, 400, 10, 10);
//			if( player.getSpecialShape()==1)
//			{
//
			//g2.fillOval(400, 400, player.getDsize(), player.getDsize());
//			}

			//player.setFancyColor("orange");
			//g2.setColor(Color.BLACK);
			//g2.drawRect(400, 400, 10, 10);

			//g2.drawOval(400, 400, 10, 10);
			
			
			//new implementation
			int shape = 0;
			
			PlayerSkin playerD = new PlayerSkin(new Player("deco", null));
			playerD.setFancyColor("orange");
			Color pColor = playerD.getColor();
			
			PlayerSizer playerDD = new PlayerSizer(playerD);
			int size = playerDD.getDsize();
			g2.setColor(pColor);
			
			
			PlayerShape playerDDD = new PlayerShape(playerDD);
			shape = playerDDD.getSpecialShape();
			if(shape==0) {
				g2.fillRect(400, 400, size, size);
				g2.drawRect(400, 400, size, size);
			}
			else
			{
				g2.fillOval(400, 400, size, size);
				g2.drawOval(400, 400, size, size);
			}
			
			
//			DecoratedPlayer playerDD = new PlayerSizer(playerD);
//			playerDD.getDsize();
//			//playerD.setFancyColor("yellow");
//			
//			decPlayers = new ArrayList<DecoratedPlayer>();
//			decPlayers.add(playerDD);

		//}
	}

	public void drawProjectiles(Graphics2D g2) {
		for (Player player : Game_Main.players) {
			for (Projectile projectile : player.liveAmmo) {
				g2.setColor(projectile.color);
				g2.fillOval(projectile.cPos.x + 3, projectile.cPos.y + 3, projectile.sizeX, projectile.sizeY);
				g2.setColor(Color.BLACK);
				g2.drawOval(projectile.cPos.x + 3, projectile.cPos.y + 3, projectile.sizeX, projectile.sizeY);
			}
		}
	}

}