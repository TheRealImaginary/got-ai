package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class DFSSearchStrategy extends SearchStrategy {

    @Override
    public State search(Problem problem) {
        Stack<State> dfsQueue = new Stack<>();
        dfsQueue.push(problem.getInitialState());
        List<State> nextStates;
        Set<State> visited = new TreeSet<>();
        while (!(dfsQueue.isEmpty())) {
            State state = dfsQueue.pop();
            if (problem.isGoal(state)) return state;
            visited.add(state);
            nextStates = problem.expand(state);
            for (State nextState : nextStates)
                if (!visited.contains(nextState))
                    dfsQueue.push(nextState);

        }
        return null;
    }
}
