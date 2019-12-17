package Visitor;

import State.MegaState;
import State.PlayerStates;
import State.State;

public class MegaVisitor implements Visitor {

    State megaState = new MegaState();

    public MegaVisitor(){

    }

    public State visit(PlayerStates playerStates) {
        return megaState;
    }
}
