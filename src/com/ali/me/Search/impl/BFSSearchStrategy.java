package com.ali.me.Search.impl;

import com.ali.me.Problem.Problem;
import com.ali.me.Search.SearchStrategy;
import com.ali.me.State.State;
import com.ali.me.State.impl.TheStateThatKnowsNothing;

import java.util.*;

public class BFSSearchStrategy extends SearchStrategy {
    @Override
    public State search(Problem problem) {
        Queue<State> queue = new LinkedList<>();
        List<State> nextStates;
        TreeSet<State> visited = new TreeSet<>((s1, s2) -> {
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
                    if (grid1[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.WHITE_WALKER) w1++;
                    if (grid2[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.WHITE_WALKER) w2++;
                }
            }
            if (w1 != w2) return w1 - w2;
            return 0;
        });
        queue.add(problem.getInitialState());
        while (!queue.isEmpty()) {
            State state = queue.poll();
            if (problem.isGoal(state)) return state;
            nextStates = problem.expand(state);
            for (State nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.add(nextState);
                }
            }
        }
        return null;
    }
}
