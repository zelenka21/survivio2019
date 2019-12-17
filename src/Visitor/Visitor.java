package Visitor;

import State.PlayerStates;
import State.State;

public interface Visitor {
    public State visit(PlayerStates playerStates);
}
