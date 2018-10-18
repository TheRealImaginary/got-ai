package com.ali.me.cost.heuristic.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;

/**
 * Zero Heuristic Used as a default for Search Strategies
 * that does not use Heuristic Cost.
 */
public class ZeroHeuristic implements Heuristic {
    @Override
    public int heuristicCost(State state) {
        return 0;
    }
}
