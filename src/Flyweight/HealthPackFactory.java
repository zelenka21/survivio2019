package Flyweight;

import Adapter.IHealth;
import gameObjects.HealthPack;
import gameObjects.LazyThreadSafeSingleton;

import java.util.HashMap;

public class HealthPackFactory {


    private static final HashMap healthPackMap = new HashMap();

    public static IHealth getHealthPack(int x, int y, int health) {

        LazyThreadSafeSingleton ltss = LazyThreadSafeSingleton.getInstance();
        HealthPack healthPack = (HealthPack)healthPackMap.get(health);

        if(healthPack == null) {
            healthPack = new HealthPack(x, y, health);
            healthPackMap.put(health, healthPack);
            ltss.log("Creating new HealthPack(" + health + " HP)\n");
        }
        return healthPack;
    }

}
