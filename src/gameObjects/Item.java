package gameObjects;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Item {
	public Point cPos;
	public Color color;
	private int xx=5,yy=5;
	public final void create(Color c) {
		
		setColor(c);
		setSize();
		
	}
	public Item(int x, int y) {
		this.cPos = new Point(x, y);
	}
	public Item()
	{}
	
	public Rectangle bounds(){
		return new Rectangle(cPos.x, cPos.y, xx, yy);
	}
	public void setColor()
	{
		this.color = Color.BLACK;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	public void setSize()
	{
		this.xx=5;
		this.yy=5;
	}

}
