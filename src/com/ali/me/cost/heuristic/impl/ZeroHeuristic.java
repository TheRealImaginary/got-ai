package com.ali.me.cost.heuristic.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;

public class ZeroHeuristic implements Heuristic {
    @Override
    public int heuristicCost(State state) {
        return 0;
    }
}
