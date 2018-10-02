package com.ali.me.Search.impl;

import com.ali.me.Problem.Problem;
import com.ali.me.Search.SearchStrategy;
import com.ali.me.State.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSearchStrategy extends SearchStrategy {
    @Override
    public State search(Problem problem) {
        Queue<State> queue = new LinkedList<>();
        queue.add(problem.getInitialState());
        while (!queue.isEmpty()) {
            State state = queue.poll();
            if (problem.isGoal(state)) return state;
            List<State> nextStates = problem.expand(state);
            queue.addAll(nextStates);
        }
        return null;
    }
}
