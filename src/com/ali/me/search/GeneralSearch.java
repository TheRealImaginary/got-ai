package com.ali.me.search;

import com.ali.me.problem.Problem;
import com.ali.me.state.State;

/**
 * General Search
 */
public class GeneralSearch {

    /**
     * Stores the number of expanded nodes.
     */
    public static long expandedNodes;

    /**
     * General Search Algorithm. It asks for a node from the Search
     * Strategy, which it tests for being a Goal state. If it is
     * this state is returned, otherwise the Problem is expanded
     * and added by the Search Strategy (Queuing Function) and the
     * search continues untill a solution is found, no solution is found
     * or the program consumes all memory and hope.
     * @param problem
     * @param searchStrategy
     * @return
     */
    public static State search(Problem problem, SearchStrategy searchStrategy) {
        expandedNodes = 0;
        searchStrategy.add(problem.getInitialState());
        while (!searchStrategy.isEmpty()) {
            State state = searchStrategy.pop();
            if (state == null) continue;
            expandedNodes++;
            if (problem.isGoal(state)) return state;
            searchStrategy.expand(problem, state);
        }
        return null;
    }
}
