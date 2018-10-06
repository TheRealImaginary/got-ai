package com.ali.me.problem;

import com.ali.me.cost.action.ActionCost;
import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;

import java.util.List;

public abstract class Problem {

    protected State initialState;

    public abstract List<State> expand(State state);

    public abstract List<State> expand(State state, Heuristic heuristic);

    public abstract List<State> expand(State state, ActionCost actionCost, Heuristic heuristic);

    public abstract boolean isGoal(State state);

    public State getInitialState() {
        return this.initialState;
    }
}
