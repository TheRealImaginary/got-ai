package com.ali.me;

import java.util.Arrays;

import com.ali.me.Problem.Problem;
import com.ali.me.Problem.impl.PlaceholderProblem;
import com.ali.me.Search.impl.DFSSearchStrategy;
import com.ali.me.State.impl.TheStateThatKnowsNothing;
import com.ali.me.State.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class Main {

	public static Problem genGrid() {
		PlaceholderProblem problem = new PlaceholderProblem(20, 20, 20, 100, 5);
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
			TheStateThatKnowsNothing nothing = (TheStateThatKnowsNothing)dfs.search(problem);
			System.out.println(nothing);
			NorthOfTheWall[][] grid = ((TheStateThatKnowsNothing)nothing).getGrid();
			for (int i = 0;i < grid.length;i++)
			    System.out.println(Arrays.toString(grid[i]));
		}
	}
	

	public static void main(String[] args) {
		new Thread(null, new Runnable() {
		    @Override
		    public void run() {
		    	Problem problem = genGrid();
        		search(problem, "dfs", false);
		    }
		}, "Please Work", 1 << 27).start();

//		NorthOfTheWall[][] gridA = new NorthOfTheWall[2][2];
//		NorthOfTheWall[][] gridB = PlaceholderProblem.copyGrid(gridA);
//	
//		
//		gridB[0][1] = NorthOfTheWall.EMPTY;
//		System.out.println("GridA = " + Arrays.deepToString(gridA));
//		System.out.println("GridB = " + Arrays.deepToString(gridB));
		
//		Problem problem = genGrid();
//		search(problem, "dfs", false);

	}
}
