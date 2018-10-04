package com.ali.me;

import java.util.Arrays;
import java.util.Stack;

import com.ali.me.cost.heuristic.impl.DragonGlassesHeuristic;
import com.ali.me.problem.Problem;
import com.ali.me.problem.impl.PlaceholderProblem;
import com.ali.me.search.impl.BFSSearchStrategy;
import com.ali.me.search.impl.DFSSearchStrategy;
import com.ali.me.search.impl.GBFSSearchStrategy;
import com.ali.me.search.impl.IterDeepSeatchStrategy;
import com.ali.me.search.impl.UCSSearchStrategy;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;
import com.ali.me.state.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class Main {

	public static Problem genGrid() {
		// PlaceholderProblem problem = new PlaceholderProblem(20, 20, 20, 100, 5);
//		PlaceholderProblem problem = new PlaceholderProblem(10, 10, 20, 20, 5);
//		PlaceholderProblem problem = new PlaceholderProblem(7, 7, 10, 10, 2);
		 PlaceholderProblem problem = new PlaceholderProblem(5, 5, 5, 5, 3);
//		 PlaceholderProblem problem = new PlaceholderProblem(4, 4, 1, 5 , 0);
		// PlaceholderProblem problem = new PlaceholderProblem(8, 8, 10, 15, 5);

		NorthOfTheWall[][] grid = ((TheStateThatKnowsNothing) problem.getInitialState()).getGrid();
		System.err.println("Initial Grid");
		for (int i = 0; i < grid.length; i++)
			System.err.println(Arrays.toString(grid[i]));
		return problem;
	}

	public static void search(Problem problem, String strategy, boolean visualize) {
		strategy = strategy.toLowerCase();
		State goalState = null;
		if (strategy.equals("dfs")) {
			DFSSearchStrategy dfs = new DFSSearchStrategy();
			goalState = dfs.search(problem);
		}
		if (strategy.equals("id")) {
			IterDeepSeatchStrategy dfs = new IterDeepSeatchStrategy();
			goalState = dfs.search(problem, 0);
		}
		if (strategy.equals("bfs")) {
			BFSSearchStrategy bfs = new BFSSearchStrategy();
			goalState = bfs.search(problem);
		}
		if (strategy.equals("ucs")) {
			UCSSearchStrategy ucs = new UCSSearchStrategy();
			goalState = ucs.search(problem);
		}
		if (strategy.equals("gbfs")) {
			GBFSSearchStrategy gbfs = new GBFSSearchStrategy(new DragonGlassesHeuristic());
			goalState = gbfs.search(problem);
		}
		if (goalState == null)
			System.err.println("Found No Solution");
		else {
			TheStateThatKnowsNothing nothing = (TheStateThatKnowsNothing) goalState;
			pathPrint(nothing);
			// System.err.println("Goal state");
			System.err.println(nothing);
			System.err.println(nothing.getDepth());
			NorthOfTheWall[][] grid = nothing.getGrid();
			for (int i = 0; i < grid.length; i++)
				System.out.println(Arrays.toString(grid[i]));
		}
	}

	public static void pathPrint(TheStateThatKnowsNothing state) {
		Stack<TheStateThatKnowsNothing> stack = new Stack<TheStateThatKnowsNothing>();
		TheStateThatKnowsNothing currState = state;

		while (currState.getParent() != null) {
			stack.push((TheStateThatKnowsNothing) currState.getParent());
			currState = (TheStateThatKnowsNothing) currState.getParent();
		}
		while (!stack.isEmpty()) {
			TheStateThatKnowsNothing currStateStack = stack.pop();
			NorthOfTheWall[][] grid = currStateStack.getGrid();
			for (int i = 0; i < grid.length; i++)
				System.out.println(Arrays.toString(grid[i]));
			System.out.println();
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new Thread(null, new Runnable() {
			@Override
			public void run() {
				Problem problem = genGrid();
				search(problem, "id", false);
				System.out.println("___________________________________________________________________________");
				search(problem, "bfs", false);
			}
		}, "Increase Stack Size", 1 << 27).start();
	}
}
