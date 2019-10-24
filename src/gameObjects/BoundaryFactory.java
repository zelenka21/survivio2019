package gameObjects;

import java.awt.Rectangle;

public class BoundaryFactory{

    public BoundaryFactory(){

    }

    public Boundary CreateBoundary(String codeLine, Rectangle bounds, int health){
        // bu
        if ("bb".equals(codeLine)) {// Breakable Boundary
            return new BreakableBoundary(bounds.x, bounds.y, bounds.width, bounds.height, health);
        } else {
            return new Boundary(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }
}
