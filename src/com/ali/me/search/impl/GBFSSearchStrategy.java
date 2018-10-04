package com.ali.me.search.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.problem.Problem;
import com.ali.me.search.SearchStrategy;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Greedy Best-First search
 */
public class GBFSSearchStrategy extends SearchStrategy {

    private static final long oo = 1L << 58;

    private Heuristic heuristic;

    public GBFSSearchStrategy(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public State search(Problem problem) {
        PriorityQueue<State> pq = new PriorityQueue<>((state1, state2) -> {
            TheStateThatKnowsNothing s1 = (TheStateThatKnowsNothing) state1;
            TheStateThatKnowsNothing s2 = (TheStateThatKnowsNothing) state2;
            return s1.getHeuristicCost() - s2.getHeuristicCost();
        });
        List<State> nextStates;
        TreeMap<State, Long> reachedCost = new TreeMap<>((s1, s2) -> {
            TheStateThatKnowsNothing state1 = (TheStateThatKnowsNothing) s1;
            TheStateThatKnowsNothing state2 = (TheStateThatKnowsNothing) s2;
            if (state1.getRow() != state2.getRow()) {
                return state1.getRow() - state2.getRow();
            }
            if (state1.getColumn() != state2.getColumn()) {
                return state1.getColumn() - state2.getColumn();
            }
            if (state1.getDragonGlasses() != state2.getDragonGlasses()) {
                return state1.getDragonGlasses() - state2.getDragonGlasses();
            }
            TheStateThatKnowsNothing.NorthOfTheWall[][] grid1 = state1.getGrid();
            TheStateThatKnowsNothing.NorthOfTheWall[][] grid2 = state2.getGrid();
            int w1 = 0;
            int w2 = 0;
            for (int i = 0; i < grid1.length; i++) {
                for (int j = 0; j < grid1[i].length; j++) {
                    if (grid1[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.W) w1++;
                    if (grid2[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.W) w2++;
                }
            }
            if (w1 != w2) return w1 - w2;
            return 0;
        });
        pq.add(problem.getInitialState());
        reachedCost.put(problem.getInitialState(), 0L);
        while (!pq.isEmpty()) {
            State state = pq.poll();
            if (problem.isGoal(state)) return state;
            long nowCost = ((TheStateThatKnowsNothing) state).getHeuristicCost();
            long beforeCost = reachedCost.getOrDefault(state, oo);
            if (nowCost > beforeCost) continue;
            nextStates = problem.expand(state);
            for (State nextState : nextStates) {
                long cost = ((TheStateThatKnowsNothing) nextState).getHeuristicCost();
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
