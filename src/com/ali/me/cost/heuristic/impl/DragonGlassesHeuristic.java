package com.ali.me.cost.heuristic.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;
import com.ali.me.state.impl.SaveWestrosState;

public class DragonGlassesHeuristic implements Heuristic {
    @Override
    public int heuristicCost(State state) {
        SaveWestrosState.NorthOfTheWall[][] grid = ((SaveWestrosState) state).getGrid();
        int numberOfWhiteWalkers = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == SaveWestrosState.NorthOfTheWall.W) numberOfWhiteWalkers++;
        return (numberOfWhiteWalkers + 3 - 1) / 3;
    }
}
