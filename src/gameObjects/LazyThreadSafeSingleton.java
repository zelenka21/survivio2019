package gameObjects;

import gameViews.Game_Main;

import javax.swing.*;
import java.awt.*;

public class LazyThreadSafeSingleton {

    private static LazyThreadSafeSingleton instance = null;

    private LazyThreadSafeSingleton() {} // Konstruktorius

    public static synchronized LazyThreadSafeSingleton getInstance() {
        if(instance == null){
            instance = new LazyThreadSafeSingleton();
        }
        //System.out.println("Returning Lazy Thread Safe Singleton instance");
        return instance;
    }

    public void log(String message) {
        Game_Main.window.connectionTextArea.append(message);
    }

}
