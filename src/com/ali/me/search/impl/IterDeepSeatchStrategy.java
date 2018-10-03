package com.ali.me.search.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import com.ali.me.problem.Problem;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;
import com.ali.me.state.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class IterDeepSeatchStrategy {
	
	
	public State search(Problem problem, int currentDepth) {
		Stack<State> dfsQueue = new Stack<>();
		dfsQueue.push(problem.getInitialState());
		List<State> nextStates;

		TreeSet<State> visited = new TreeSet<>(new Comparator<State>() {
			@Override
			public int compare(State s1, State s2) {
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
				NorthOfTheWall[][] grid1 = state1.getGrid();
				NorthOfTheWall[][] grid2 = state2.getGrid();
				int w1 = 0;
				int w2 = 0;
				for (int i = 0; i < grid1.length; i++) {
					for (int j = 0; j < grid1[i].length; j++) {
						if (grid1[i][j] == NorthOfTheWall.WHITE_WALKER)
							w1++;
						if (grid2[i][j] == NorthOfTheWall.WHITE_WALKER)
							w2++;
					}
				}
				if (w1 != w2)
					return w1 - w2;
				return 0;
			}
		});

		while (!(dfsQueue.isEmpty())) {
			State state = dfsQueue.pop();
			int depth = ((TheStateThatKnowsNothing) state).getDepth();
			System.out.println(depth + " > " + currentDepth);
			if(((TheStateThatKnowsNothing) state).getDepth() > currentDepth) {
				continue;
			}
			
			
			if (problem.isGoal(state))
				return state;
			
			visited.add(state);
			nextStates = problem.expand(state);
			for (State nextState : nextStates)
				if (!visited.contains(nextState))
					dfsQueue.push(nextState);

		}
		return search(problem, ++currentDepth);
	}

}
