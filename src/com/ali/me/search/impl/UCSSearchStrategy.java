package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.*;

/**
 * Uniform Cost Search Startegy (UCS Queue-ing Function)
 */
public class UCSSearchStrategy extends SearchStrategy {

    /**
     * PriorityQueue (Min Heap) for Removing
     * States in increasing order of cost.
     */
    private PriorityQueue<State> pq;

    /**
     * Map for mapping each State to its cost.
     */
    private Map<State, Long> reachedCost;

    /**
     * Creates a new UCS Search Strategy and
     * initializes the above structures to be empty.
     */
    public UCSSearchStrategy() {
        pq = new PriorityQueue<>(Comparator.comparingInt(State::getPathCost));
        reachedCost = new TreeMap<>();
    }

    @Override
    public void expand(Problem problem, State state) {
        List<State> nextStates = problem.expand(state);
        for (State nextState : nextStates)
            this.add(nextState);
    }

    @Override
    public boolean add(State state) {
        long cost = state.getPathCost();
        long beforeCost = reachedCost.getOrDefault(state, POSITIVE_INFINITY);
        if (cost < beforeCost) {
            this.reachedCost.put(state, cost);
            this.pq.add(state);
            return true;
        }
        return false;
    }

    @Override
    public State pop() {
        while (!this.pq.isEmpty()) {
            State state = this.pq.poll();
            long nowCost = state.getPathCost();
            long beforeCost = this.reachedCost.getOrDefault(state, POSITIVE_INFINITY);
            if (nowCost > beforeCost) continue;
            return state;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.pq.isEmpty();
    }
}
