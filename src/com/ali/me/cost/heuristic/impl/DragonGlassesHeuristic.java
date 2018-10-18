package com.ali.me.cost.heuristic.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;
import com.ali.me.state.impl.SaveWesterosState;

/**
 * Calculates an estimate of the number of Dragon Glasses needed.
 */
public class DragonGlassesHeuristic implements Heuristic {
    /**
     * Returns an estimate of the number of Dragon Glasses needed. It does
     * so by counting the number of White Walkers and dividing this number
     * by `3`. This assumes Jon can always kill 3 White Walkers at a time.
     * @param state
     * @return
     */
    @Override
    public int heuristicCost(State state) {
        SaveWesterosState.NorthOfTheWall[][] grid = ((SaveWesterosState) state).getGrid();
        int numberOfWhiteWalkers = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == SaveWesterosState.NorthOfTheWall.W) numberOfWhiteWalkers++;
        return (numberOfWhiteWalkers + 3 - 1) / 3;
    }
}
