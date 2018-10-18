package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.*;

/**
 * BFS Search Strategy (BFS Queue-ing Function)
 */
public class BFSSearchStrategy extends SearchStrategy {

    /**
     * Queue for Adding and Removing States
     * in a FIFO manner.
     */
    private Queue<State> queue;

    /**
     * Set for checking for visited States.
     */
    private Set<State> visited;

    /**
     * Creates a new BFS Search Strategy and
     * initializes the above structures to be empty.
     */
    public BFSSearchStrategy() {
        this.queue = new LinkedList<>();
        this.visited = new TreeSet<>();
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
        this.visited.add(state);
        this.queue.add(state);
        return true;
    }

    @Override
    public State pop() {
        return this.queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
