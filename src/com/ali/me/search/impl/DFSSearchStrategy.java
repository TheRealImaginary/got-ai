package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * DFS Search Strategy (DFS Queue-ing Function)
 */
public class DFSSearchStrategy extends SearchStrategy {

    /**
     * Stack for Adding and Removing States
     * in a LIFO manner.
     */
    private Stack<State> stack;

    /**
     * Set for checking for visited States.
     */
    private Set<State> visited;

    /**
     * Creates a new DFS Search Strategy and
     * initializes the above structures to be empty.
     */
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
