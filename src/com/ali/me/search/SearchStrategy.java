package com.ali.me.search;

import com.ali.me.problem.Problem;
import com.ali.me.state.State;

/**
 * Abstract Search Strategy (Queue-ing Function)
 */
public abstract class SearchStrategy {

    /**
     * Positive Infinity. Used in UCS, GBFS and A*.
     */
    protected static final long POSITIVE_INFINITY = 1L << 58;

    /**
     * Expands the given State using the given Problem.
     * @param problem
     * @param state
     */
    public abstract void expand(Problem problem, State state);

    /**
     * Adds the given State to the structure.
     * @param state
     * @return
     */
    protected abstract boolean add(State state);

    /**
     * Removes and return a State from the structure.
     * @return
     */
    protected abstract State pop();

    /**
     * Returns true if the structure is empty, false otherwise.
     * @return
     */
    protected abstract boolean isEmpty();
}
