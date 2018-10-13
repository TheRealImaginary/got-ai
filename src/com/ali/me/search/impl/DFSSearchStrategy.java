package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class DFSSearchStrategy extends SearchStrategy {

    private Stack<State> stack;
    private Set<State> visited;

    public DFSSearchStrategy() {
        stack = new Stack<>();
        visited = new TreeSet<>();
    }

    @Override
    public void expand(Problem problem, State state) {
        List<State> nextStates = problem.expand(state);
        for (State nextState : nextStates)
            this.add(nextState);
    }

    @Override
    public boolean add(State state) {
        if (this.visited.contains(state)) return false;
        this.stack.push(state);
        return true;
    }

    @Override
    public State pop() {
        State state = this.stack.pop();
        this.visited.add(state);
        return state;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
