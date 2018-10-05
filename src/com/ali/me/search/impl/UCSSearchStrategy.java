package com.ali.me.search.impl;

import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class UCSSearchStrategy extends SearchStrategy {

    private static final long oo = 1L << 58;

    @Override
    public State search(Problem problem) {
        PriorityQueue<State> pq = new PriorityQueue<>((state1, state2) -> {
            TheStateThatKnowsNothing s1 = (TheStateThatKnowsNothing) state1;
            TheStateThatKnowsNothing s2 = (TheStateThatKnowsNothing) state2;
            return s1.getPathCost() - s2.getPathCost();
        });
        List<State> nextStates;
        TreeMap<State, Long> reachedCost = new TreeMap<>();
        pq.add(problem.getInitialState());
        reachedCost.put(problem.getInitialState(), 0L);
        while (!pq.isEmpty()) {
            State state = pq.poll();
            if (problem.isGoal(state)) return state;
            long nowCost = ((TheStateThatKnowsNothing) state).getPathCost();
            long beforeCost = reachedCost.getOrDefault(state, oo);
            if (nowCost > beforeCost) continue;
            nextStates = problem.expand(state);
            for (State nextState : nextStates) {
                long cost = ((TheStateThatKnowsNothing) nextState).getPathCost();
                beforeCost = reachedCost.getOrDefault(nextState, oo);
                if (cost < beforeCost) {
                    reachedCost.put(nextState, cost);
                    pq.add(nextState);
                }
            }
        }
        return null;
    }
}
