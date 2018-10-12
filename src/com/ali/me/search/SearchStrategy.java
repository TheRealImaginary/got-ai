package com.ali.me.search;

import com.ali.me.problem.Problem;
import com.ali.me.state.State;

/**
 * This class describes the structure of a Search Strategy. Implementing Sub-classes
 * must implement and define the behaviour of the following abstract method.
 */
public abstract class SearchStrategy {
    /**
     * Searches the state space of the given problem, starting from its Initial state
     * and returns either a Goal state satisfying {@link Problem#isGoal} method or null
     * if no solution is found.
     * @param problem The problem for which a solution is to be searched
     * @return A Goal state satisfying {@link Problem#isGoal} method or null if no solution is found
     */
    public abstract State search(Problem problem);
}
