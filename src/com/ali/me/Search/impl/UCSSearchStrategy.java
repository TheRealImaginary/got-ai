package com.ali.me.Search.impl;

import com.ali.me.Problem.Problem;
import com.ali.me.Search.SearchStrategy;
import com.ali.me.State.State;
import com.ali.me.State.impl.TheStateThatKnowsNothing;

import java.util.List;
import java.util.PriorityQueue;

public class UCSSearchStrategy extends SearchStrategy {
    @Override
    public State search(Problem problem) {
        PriorityQueue<State> pq = new PriorityQueue<>((state1, state2) -> {
            TheStateThatKnowsNothing s1 = (TheStateThatKnowsNothing) state1;
            TheStateThatKnowsNothing s2 = (TheStateThatKnowsNothing) state2;
            return s1.getCost() - s2.getCost();
        });
        pq.add(problem.getInitialState());
        while (!pq.isEmpty()) {
            State state = pq.poll();
            if (problem.isGoal(state)) return state;
            List<State> nextStates = problem.expand(state);
            pq.addAll(nextStates);
        }
        return null;
    }
}
