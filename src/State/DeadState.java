package State;

import gameObjects.Player;

import java.awt.Color;

public class DeadState implements State {

    @Override
    public void changeSpeed(Player p) {
        p.setSpeed(0);
    }

    @Override
    public void changeLooks(Player p) {

        p.specColor = Color.GRAY;

    }

}
