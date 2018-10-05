package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.*;

public class BFSSearchStrategy extends SearchStrategy {

    @Override
    public State search(Problem problem) {
        Queue<State> queue = new LinkedList<>();
        List<State> nextStates;
        Set<State> visited = new TreeSet<>();
        visited.add(problem.getInitialState());
        queue.add(problem.getInitialState());
        while (!queue.isEmpty()) {
            State state = queue.poll();
            if (problem.isGoal(state)) return state;
            nextStates = problem.expand(state);
            for (State nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.add(nextState);
                }
            }
        }
        return null;
    }
}
