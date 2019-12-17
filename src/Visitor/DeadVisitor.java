package Visitor;

import State.DeadState;
import State.PlayerStates;
import State.State;

public class DeadVisitor implements Visitor {

    State deadState = new DeadState();

    public DeadVisitor(){

    }

    public State visit(PlayerStates playerStates) { return deadState; }
}
