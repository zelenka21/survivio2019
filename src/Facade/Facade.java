package Facade;

import Adapter.IHealth;
import Adapter.IMegaHealth;
import Adapter.MegaHPAdapter;
import Adapter.MegaHealthPack;
import Decorator.DecoratedPlayer;
import Strategy.*;
import gameObjects.*;

import java.util.ArrayList;

public class Facade {
    private IDropStrategy dropAm;
    private IDropStrategy dropHP;
    private IMegaHealth megaHealth;
    private IHealth health;
    private BreakableBoundary breakableBoundary;

    public Facade() {
        dropAm = new DropAmmo();
        dropHP = new DropHealth();
    }


    public void dropAmmo(Item item, Map map, Player eplayer, int erx, int ery) {
        dropAm.dropItem(item, map, eplayer, erx, ery);
    }

    public void dropHealth(Item item, Map map, Player eplayer, int erx, int ery) {
        dropHP.dropItem(item, map, eplayer, erx, ery);
    }

    public void boundaryTakeDamage(BreakableBoundary bound, int i) {
        bound.takeDamage(i);
    }

    public void boundaryDestroy(BreakableBoundary bound) {
        bound.destroy();
    }

    public int boundaryGetHealth(BreakableBoundary bound) {
        return bound.getHealth();
    }

}
