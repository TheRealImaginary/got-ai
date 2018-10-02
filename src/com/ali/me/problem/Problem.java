package com.ali.me.problem;

import com.ali.me.cost.action.ActionCost;
import com.ali.me.cost.action.impl.PlaceholderActionCost;
import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.cost.heuristic.impl.ZeroHeuristic;
import com.ali.me.state.State;

import java.util.List;

public abstract class Problem {

    protected State initialState;

    public List<State> expand(State state) {
        return expand(state, new PlaceholderActionCost(), new ZeroHeuristic());
    }

    public abstract List<State> expand(State state, ActionCost actionCost, Heuristic heuristic);

    public abstract boolean isGoal(State state);

    public abstract State getInitialState();

}
