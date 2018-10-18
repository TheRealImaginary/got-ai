package com.ali.me.cost.heuristic.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;
import com.ali.me.state.impl.SaveWesterosState;

/**
 * Computes the Manhattan Distance to the nearest Objective.
 */
public class NearestNextObjectiveHeuristic implements Heuristic {

    /**
     * Given a state, this will return the Manhattan Distance to the nearest
     * objective. An Objective is either a Dragon Stone if Jon does not have
     * enough Dragon Glasses (0) otherwise a White Walker.
     * @param state
     * @return
     */
    @Override
    public int heuristicCost(State state) {
        SaveWesterosState saveWesterosState = (SaveWesterosState) state;
        SaveWesterosState.NorthOfTheWall[][] grid = saveWesterosState.getGrid();
        int row = saveWesterosState.getRow();
        int col = saveWesterosState.getColumn();
        int dragonGlasses = saveWesterosState.getDragonGlasses();
        if (dragonGlasses == 0) return manhattanDistance(grid, SaveWesterosState.NorthOfTheWall.D, row, col);
        return manhattanDistance(grid, SaveWesterosState.NorthOfTheWall.W, row, col);
    }

    /**
     * Given the Grid, a Target cell and Jon's position, this returns
     * the Manhattan Distance to the nearest Target Cell from Jon's Position.
     * @param grid
     * @param target
     * @param x
     * @param y
     * @return
     */
    private int manhattanDistance(SaveWesterosState.NorthOfTheWall[][] grid, SaveWesterosState.NorthOfTheWall target, int x, int y) {
        int minimumManhattanDistance = -1;
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == target) {
                    int distance = Math.abs(row - x) + Math.abs(col - y);
                    if (minimumManhattanDistance == -1 || distance < minimumManhattanDistance)
                        minimumManhattanDistance = distance;
                }
            }
        return minimumManhattanDistance == -1 ? 0 : minimumManhattanDistance;
    }
}
