package com.ali.me.cost.heuristic.impl;

import java.util.Arrays;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.problem.impl.PlaceholderProblem;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;
import com.ali.me.state.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class NoMoveRestrictionAdmissHeuristic implements Heuristic {
	public static void doNothing() {

	}

	public int heuristicCost(State state) {

		TheStateThatKnowsNothing.NorthOfTheWall[][] grid = PlaceholderProblem
				.copyGrid(((TheStateThatKnowsNothing) state).getGrid());
		int numberOfWhiteWalkers = 0;
		for (int lookFor = 3; lookFor != 0; lookFor--)
			for (int i = 0; i < grid.length; i++)
				for (int j = 0; j < grid[i].length; j++)
					if (grid[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.E
							|| grid[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.W
							|| grid[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.O) {
						int counter = 0;
						boolean up = false;
						boolean down = false;
						boolean left = false;
						boolean right = false;
						
						if(i> 0 && i<grid.length-1 &j> 0 && j< grid.length -1) {
							if (grid[i][j+1] == NorthOfTheWall.W) {
								counter++;
								right = true;
							}
							if (grid[i][j-1] == NorthOfTheWall.W) {
								counter++;
								left = true;
							}
							if (grid[i+1][j] == NorthOfTheWall.W) {
								counter++;
								down = true;
							}
							if (grid[i-1][j] == NorthOfTheWall.W) {
								counter++;
								up = true;
							}
							
							if(counter == lookFor) {
								numberOfWhiteWalkers++;
								if(up) {
									grid[i-1][j] = NorthOfTheWall.E;
								}
								if(down) {
									grid[i+1][j] = NorthOfTheWall.E;
								}
								if(left) {
									grid[i][j-1] = NorthOfTheWall.E;
								}
								if(right) {
									grid[i][j+1] = NorthOfTheWall.E;
								}
							}
							if(counter == 4) {
								numberOfWhiteWalkers++;
								if(up) {
									grid[i-1][j] = NorthOfTheWall.E;
								}
								if(down) {
									grid[i+1][j] = NorthOfTheWall.E;
								}
								if(left) {
									grid[i][j-1] = NorthOfTheWall.E;
								}
							} 
							
						}
					}

		for (int i = 0; i < grid.length; i++)
			System.out.println(Arrays.toString(grid[i]));

		return numberOfWhiteWalkers;

	}

}
