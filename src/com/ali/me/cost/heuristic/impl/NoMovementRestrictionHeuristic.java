package com.ali.me.cost.heuristic.impl;

import java.util.Arrays;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.problem.impl.PlaceholderProblem;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;
import com.ali.me.state.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class NoMovementRestrictionHeuristic implements Heuristic {

	public int heuristicCost(State state) {

		TheStateThatKnowsNothing.NorthOfTheWall[][] grid = PlaceholderProblem
				.copyGrid(((TheStateThatKnowsNothing) state).getGrid());
		int numberOfWhiteWalkers = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++)
				if (grid[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.E) {

					if (i == 0 & j == 0) {
						if (grid[i + 1][j] == NorthOfTheWall.W || grid[i][j + 1] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i + 1][j] = NorthOfTheWall.E;
							grid[i][j + 1] = NorthOfTheWall.E;
						}
					} else if (i == 0 && j == grid.length - 1) {
						if (grid[i + 1][j] == NorthOfTheWall.W || grid[i][j - 1] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i + 1][j] = NorthOfTheWall.E;
							grid[i][j - 1] = NorthOfTheWall.E;
						}
					} else if (i == grid.length - 1 && j == 0) {
						if (grid[i - 1][j] == NorthOfTheWall.W || grid[i][j + 1] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i - 1][j] = NorthOfTheWall.E;
							grid[i][j + 1] = NorthOfTheWall.E;
						}
					} else if (i == grid.length - 1 && j == grid.length) {
						if (grid[i - 1][j] == NorthOfTheWall.W || grid[i][j - 1] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i - 1][j] = NorthOfTheWall.E;
							grid[i][j - 1] = NorthOfTheWall.E;
						}
					} else if (i == 0 && j != 0) {
						if (grid[i + 1][j] == NorthOfTheWall.W || grid[i][j + 1] == NorthOfTheWall.W
								|| grid[i][j - 1] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i + 1][j] = NorthOfTheWall.E;
							grid[i][j + 1] = NorthOfTheWall.E;
							grid[i][j - 1] = NorthOfTheWall.E;
						}
					} else if (i == grid.length - 1 && j != 0) {
						if (grid[i - 1][j] == NorthOfTheWall.W || grid[i][j + 1] == NorthOfTheWall.W
								|| grid[i][j - 1] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i - 1][j] = NorthOfTheWall.E;
							grid[i][j + 1] = NorthOfTheWall.E;
							grid[i][j - 1] = NorthOfTheWall.E;
						}
					} else if (j == 0 && i != 0) {
						if (grid[i][j + 1] == NorthOfTheWall.W || grid[i + 1][j] == NorthOfTheWall.W
								|| grid[i - 1][j] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i][j + 1] = NorthOfTheWall.E;
							grid[i + 1][j] = NorthOfTheWall.E;
							grid[i - 1][j] = NorthOfTheWall.E;
						}
					} else if (j == grid.length - 1 && i != 0) {
						if (grid[i][j - 1] == NorthOfTheWall.W || grid[i + 1][j] == NorthOfTheWall.W
								|| grid[i - 1][j] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i][j - 1] = NorthOfTheWall.E;
							grid[i + 1][j] = NorthOfTheWall.E;
							grid[i - 1][j] = NorthOfTheWall.E;
						}
					} else {
						if (grid[i][j - 1] == NorthOfTheWall.W || grid[i + 1][j] == NorthOfTheWall.W
								|| grid[i - 1][j] == NorthOfTheWall.W || grid[i][j + 1] == NorthOfTheWall.W) {
							numberOfWhiteWalkers++;
							grid[i][j - 1] = NorthOfTheWall.E;
							grid[i + 1][j] = NorthOfTheWall.E;
							grid[i - 1][j] = NorthOfTheWall.E;
							grid[i][j + 1] = NorthOfTheWall.E;
						}
					}

					// We might want to revisit this
					// if (i < 2) {
					// grid[i+2][j] = NorthOfTheWall.E;
					// }else
					// if(i > grid.length-3) {
					// grid[i-2][j] = NorthOfTheWall.E;
					// }else {
					// grid[i+2][j] = NorthOfTheWall.E;
					// grid[i-2][j] = NorthOfTheWall.E;
					// }
					// if (j < 2) {
					// grid[i][j+2] = NorthOfTheWall.E;
					// }else
					// if(j > grid[i].length-3) {
					// grid[i][j-2] = NorthOfTheWall.E;
					// }else {
					// grid[i][j+2] = NorthOfTheWall.E;
					// grid[i][j-2] = NorthOfTheWall.E;
					// }
					//
					// if (i == 0 && j == 0) {
					// grid[i+1][j+1] = NorthOfTheWall.E;
					// }
					// if (i == 0 && j == grid.length - 1) {
					// grid[i+1][j+1] = NorthOfTheWall.E;
					// }
					// if (i == grid.length - 1 && j == 0) {
					// grid[i-1][j-1] = NorthOfTheWall.E;
					// }
					// if (i == grid.length - 1 && j == 1) {
					// grid[i+1][j+1] = NorthOfTheWall.E;
					// }
					//
					// if(i == 0 & j != 0 & j != grid.length -1) {
					// grid[i+1][j+1] = NorthOfTheWall.E;
					// grid[i+1][j-1] = NorthOfTheWall.E;
					// }
					// if(i == grid.length-1 && j != 0 || j != grid.length - 1) {
					// System.out.println(i +"+"+ j);
					//
					// grid[i+1][j+1] = NorthOfTheWall.E;
					// grid[i-1][j+1] = NorthOfTheWall.E;
					// }
					//
					// if(j == 0 & i != 0 & i != grid.length) {
					// grid[i+1][j+1] = NorthOfTheWall.E;
					// grid[i-1][j+1] = NorthOfTheWall.E;
					// }
					// if(j == grid.length-1 & i != 0 || i != grid.length -1) {
					// grid[i+1][j-1] = NorthOfTheWall.E;
					// grid[i-1][j-1] = NorthOfTheWall.E;
					// }
					//
					// if(i != 0 || i != grid.length-1 && j != 0 || j != grid.length -1) {
					// grid[i+1][j+1] = NorthOfTheWall.E;
					// grid[i+1][j-1] = NorthOfTheWall.E;
					// grid[i-1][j+1] = NorthOfTheWall.E;
					// grid[i-1][j-1] = NorthOfTheWall.E;
					// }
					//
				}
		for (int i = 0; i < grid.length; i++)
			System.out.println(Arrays.toString(grid[i]));

		return numberOfWhiteWalkers;

	}
}
