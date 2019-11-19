package gameObjects;

import java.awt.Color;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import gameViews.Game_Main;

public class AmmoPack extends Item{

public int amount;
	
	public AmmoPack(int x, int y, int amount){
		super(x, y);
		
		this.amount = amount;
		this.color = Color.GRAY;
		
//		URL file = Game_Main.class.getClassLoader().getResource("/images/ammo_pack.bmp");
//		try {
//			this.image = ImageIO.read(new File(file.toString().substring(6)));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
	@Override
	public Rectangle bounds(){
		return new Rectangle(cPos.x, cPos.y, 7, 7);
	}

}
