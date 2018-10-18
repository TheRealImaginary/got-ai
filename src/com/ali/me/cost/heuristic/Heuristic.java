package com.ali.me.cost.heuristic;

import com.ali.me.state.State;

/**
 * Heuristic Function
 */
public interface Heuristic {

    /**
     * Calculates and returns the Heuristic cost
     * for a given State.
     * @param state
     * @return
     */
    int heuristicCost(State state);
}
