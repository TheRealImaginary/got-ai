package com.ali.me.search;

import com.ali.me.problem.Problem;
import com.ali.me.state.State;

public class GeneralSearch {

    public static State search(Problem problem, SearchStrategy searchStrategy) {
        searchStrategy.add(problem.getInitialState());
        while (!searchStrategy.isEmpty()) {
            State state = searchStrategy.pop();
            if (state == null) continue;
            if (problem.isGoal(state)) return state;
            searchStrategy.expand(problem, state);
        }
        return null;
    }
}
