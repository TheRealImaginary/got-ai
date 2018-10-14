package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.*;

public class BFSSearchStrategy extends SearchStrategy {

    private Queue<State> queue;
    private Set<State> visited;

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
