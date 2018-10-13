package com.ali.me.search.impl;

import java.util.List;
import java.util.Stack;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

public class IterativeDeepeningSearchStrategy extends SearchStrategy {

    private Stack<State> stack;
    private int depthLimit;

    public IterativeDeepeningSearchStrategy(int depthLimit) {
        this.stack = new Stack<>();
        this.depthLimit = depthLimit;
    }

    @Override
    public void expand(Problem problem, State state) {
        List<State> nextStates = problem.expand(state);
        for (State nextState : nextStates)
            this.add(nextState);
    }

    @Override
    public boolean add(State state) {
        if (state.getDepth() == depthLimit) return false;
        this.stack.push(state);
        return true;
    }

    @Override
    public State pop() {
        return this.stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
