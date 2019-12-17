package Visitor;

import State.RegularState;
import State.PlayerStates;
import State.State;

public class RegularVisitor implements Visitor {

    State regularState = new RegularState();

    public RegularVisitor(){

    }

    public State visit(PlayerStates playerStates) { return regularState; }
}
