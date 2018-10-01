package com.ali.me;

import java.util.Arrays;

import com.ali.me.Problem.Problem;
import com.ali.me.Problem.impl.PlaceholderProblem;
import com.ali.me.Search.impl.DFSSearchStrategy;
import com.ali.me.State.impl.TheStateThatKnowsNothing;
import com.ali.me.State.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class Main {

	public static Problem genGrid() {
		PlaceholderProblem problem = new PlaceholderProblem(5, 5, 5, 5, 1);
		NorthOfTheWall[][] grid = ((TheStateThatKnowsNothing)problem.getInitialState()).getGrid();
		
//		for(int i = 0; i < grid.length; i ++) {
//			for(int j = 0; j < grid[i].length; j++) {
//				System.out.println(grid[i][j]);
//			}
//		}
		
		for (int i = 0;i < grid.length;i++)
		    System.out.println(Arrays.toString(grid[i]));
		
		  
		return problem;
	}

	public static void search(Problem problem, String strategy, boolean visualize) {
		if(strategy.equals("dfs")) {
			DFSSearchStrategy dfs = new DFSSearchStrategy();
			System.out.println(dfs.search(problem));
		}
	}

	public static void main(String[] args) {
		Problem problem = genGrid();
		search(problem, "dfs", false);

	}
}
