package com.ali.me.search.impl;

import java.util.*;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

public class AStarSearchStrategy extends SearchStrategy {

    private Heuristic heuristic;
    private PriorityQueue<State> pq;
    private Map<State, Long> reachedCost;


    public AStarSearchStrategy(Heuristic heuristic) {
        this.heuristic = heuristic;
        pq = new PriorityQueue<>(Comparator.comparing((State s) -> s.getPathCost() + s.getHeuristicCost()));
        reachedCost = new TreeMap<>();
    }

    @Override
    public void expand(Problem problem, State state) {
        List<State> nextStates = problem.expand(state, this.heuristic);
        for (State nextState : nextStates)
            this.add(nextState);
    }

    @Override
    protected boolean add(State state) {
        long cost = state.getHeuristicCost() + state.getPathCost();
        long beforeCost = reachedCost.getOrDefault(state, POSITIVE_INFINITY);
        if (cost < beforeCost) {
            this.reachedCost.put(state, cost);
            this.pq.add(state);
            return true;
        }
        return false;
    }

    @Override
    protected State pop() {
        while (!this.pq.isEmpty()) {
            State state = this.pq.poll();
            long nowCost = state.getHeuristicCost() + state.getPathCost();
            long beforeCost = this.reachedCost.getOrDefault(state, POSITIVE_INFINITY);
            if (nowCost > beforeCost) continue;
            return state;
        }
        return null;
    }

    @Override
    protected boolean isEmpty() {
        return this.pq.isEmpty();
    }

    public State search(Problem problem) {
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparing((State s) -> s.getPathCost() + s.getHeuristicCost()));
        List<State> nextStates;
        TreeMap<State, Long> reachedCost = new TreeMap<>();
        pq.add(problem.getInitialState());
        reachedCost.put(problem.getInitialState(), 0L);
        while (!pq.isEmpty()) {
            State state = pq.poll();
            if (problem.isGoal(state)) return state;
            long nowCost = state.getHeuristicCost() + state.getPathCost();
            long beforeCost = reachedCost.getOrDefault(state, POSITIVE_INFINITY);
            if (nowCost > beforeCost) continue;
            nextStates = problem.expand(state, this.heuristic);
            for (State nextState : nextStates) {
                long cost = nextState.getHeuristicCost() + nextState.getPathCost();
                beforeCost = reachedCost.getOrDefault(nextState, POSITIVE_INFINITY);
                if (cost < beforeCost) {
                    reachedCost.put(nextState, cost);
                    pq.add(nextState);
                }
            }
        }
        return null;
    }
}
