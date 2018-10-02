package com.ali.me.Problem;

import com.ali.me.State.State;

import java.util.List;

public abstract class Problem {

    protected State initialState;

    public abstract List<State> expand(State state);

    public abstract boolean isGoal(State state);
    
    public abstract State getInitialState();

}
