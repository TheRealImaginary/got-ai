package com.ali.me.cost.heuristic.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;
import com.ali.me.state.impl.SaveWesterosState;

public class NearestWhiteWalkerHeuristic implements Heuristic {

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
