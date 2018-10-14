package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.*;

public class UCSSearchStrategy extends SearchStrategy {

    private PriorityQueue<State> pq;
    private Map<State, Long> reachedCost;

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
