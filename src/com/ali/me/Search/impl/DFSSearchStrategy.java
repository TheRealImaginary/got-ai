package com.ali.me.Search.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import com.ali.me.Problem.Problem;
import com.ali.me.Search.SearchStrategy;
import com.ali.me.State.State;
import com.ali.me.State.impl.TheStateThatKnowsNothing;
import com.ali.me.State.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class DFSSearchStrategy extends SearchStrategy {

	@Override
	public State search(Problem problem) {

		// State state = problem.getInitialState();
		//
		// dfsQueue.push(state);

		Stack<State> dfsQueue = new Stack<State>();
		dfsQueue.push(problem.getInitialState());
		List<State> nextStatesInit;

		TreeSet<State> tree = new TreeSet<State>(new Comparator<State>() {
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
						if (grid1[i][j] == NorthOfTheWall.WHITE_WALKER) {
							w1++;
						}
						if (grid2[i][j] == NorthOfTheWall.WHITE_WALKER) {
							w2++;
						}
					}
				}

				if (w1 != w2) {
					return w1 - w2;
				}

				return 0;
			}
		});

		while (!(dfsQueue.isEmpty())) {
			State state = dfsQueue.pop();
			if (problem.isGoal(state)) {
				return state;
			}

			TheStateThatKnowsNothing newState = (TheStateThatKnowsNothing) state;

			NorthOfTheWall[][] grid = ((TheStateThatKnowsNothing) state).getGrid();

			System.out.println(newState);
			for (int i = 0; i < grid.length; i++)
				System.out.println(Arrays.toString(grid[i]));
			System.out.println();
			tree.add(state);
			nextStatesInit = problem.expand(state);
			for (int i = 0; i < nextStatesInit.size(); i++) {
				if (!(tree.contains(nextStatesInit.get(i)))) {
					dfsQueue.push(nextStatesInit.get(i));
				}
			}
		}
		return null;
	}
}
