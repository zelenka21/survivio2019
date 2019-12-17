package Visitor;

import State.LowState;
import State.PlayerStates;
import State.State;

public class LowVisitor implements Visitor {

    State lowState = new LowState();

    public LowVisitor(){

    }

    public State visit(PlayerStates playerStates) {
        return lowState;
    }
}
