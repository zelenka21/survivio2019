package gameViews;

import Adapter.IHealth;
import Facade.Facade;
import Flyweight.HealthPackFactory;
import gameObjects.HealthPack;
import gameObjects.Map;
import gameObjects.Player;

public class ThreadB extends Thread{
    long total;
    Facade fc;
    Player eplayer;
    int health;
    Map map;
    int x;
    int y;

    public ThreadB(Facade fc, Player eplayer, int health, Map map, int x, int y) {
        this.fc = fc;
        this.eplayer = eplayer;
        this.health = health;
        this.map = map;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run(){
        synchronized(this){
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < 100000; ++j){
                fc.dropHealth((HealthPack) HealthPackFactory.getHealthPack(eplayer.cPos.x, eplayer.cPos.y, health), map, eplayer, x, y);
            }
            long endTime = System.currentTimeMillis();
            total = endTime - startTime;
            notify();
        }
    }
}
