package com.ali.me.search.impl;

import java.util.List;
import java.util.Stack;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;

public class IterDeepSeatchStrategy extends SearchStrategy {

    @Override
    public State search(Problem problem) {
        return search(problem, 0);
    }

    public State search(Problem problem, int depthLimit) {
        Stack<State> dfsQueue = new Stack<>();
        dfsQueue.push(problem.getInitialState());
        List<State> nextStates;
        while (!(dfsQueue.isEmpty())) {
            State state = dfsQueue.pop();
            if (problem.isGoal(state)) return state;
            int depth = ((TheStateThatKnowsNothing) state).getDepth();
            if (depth == depthLimit)
                continue;
            nextStates = problem.expand(state);
            dfsQueue.addAll(nextStates);
        }
        return search(problem, ++depthLimit);
    }
}
