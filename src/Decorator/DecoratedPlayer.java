package Decorator;

import java.awt.Color;

public interface DecoratedPlayer {
	
	
	void setColor(Color color);
    void setFancyColor(String color);
    Color getColor();
    int getDsize();
	
    
}
