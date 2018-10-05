package com.ali.me.search.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Greedy Best-First search
 */
public class GBFSSearchStrategy extends SearchStrategy {

    private static final long POSITIVE_INFINITY = 1L << 58;

    private Heuristic heuristic;

    public GBFSSearchStrategy(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public State search(Problem problem) {
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(State::getHeuristicCost));
        List<State> nextStates;
        TreeMap<State, Long> reachedCost = new TreeMap<>();
        pq.add(problem.getInitialState());
        reachedCost.put(problem.getInitialState(), 0L);
        while (!pq.isEmpty()) {
            State state = pq.poll();
            if (problem.isGoal(state)) return state;
            long nowCost = state.getHeuristicCost();
            long beforeCost = reachedCost.getOrDefault(state, POSITIVE_INFINITY);
            if (nowCost > beforeCost) continue;
            nextStates = problem.expand(state);
            for (State nextState : nextStates) {
                long cost = nextState.getHeuristicCost();
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
