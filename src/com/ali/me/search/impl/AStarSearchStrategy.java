package com.ali.me.search.impl;

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

import com.ali.me.cost.action.impl.PlaceholderActionCost;
import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;

public class AStarSearchStrategy extends SearchStrategy {


    private static final long oo = 1L << 58;

    private Heuristic heuristic;

    public AStarSearchStrategy(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public State search(Problem problem) {
        PriorityQueue<State> pq = new PriorityQueue<>((state1, state2) -> {
            TheStateThatKnowsNothing s1 = (TheStateThatKnowsNothing) state1;
            TheStateThatKnowsNothing s2 = (TheStateThatKnowsNothing) state2;
            return (s1.getHeuristicCost() + s1.getPathCost()) - (s2.getHeuristicCost() + s2.getPathCost());
        });
        List<State> nextStates;
        TreeMap<State, Long> reachedCost = new TreeMap<>();
        pq.add(problem.getInitialState());
        reachedCost.put(problem.getInitialState(), 0L);
        while (!pq.isEmpty()) {
            State state = pq.poll();
            if (problem.isGoal(state)) return state;
            long nowCost = ((TheStateThatKnowsNothing) state).getHeuristicCost() + ((TheStateThatKnowsNothing) state).getPathCost();
            long beforeCost = reachedCost.getOrDefault(state, oo);
            if (nowCost > beforeCost) continue;
            nextStates = problem.expand(state, new PlaceholderActionCost(), this.heuristic);
            for (State nextState : nextStates) {
                long cost = ((TheStateThatKnowsNothing) nextState).getHeuristicCost() + ((TheStateThatKnowsNothing) nextState).getPathCost();
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
