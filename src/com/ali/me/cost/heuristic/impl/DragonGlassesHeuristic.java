package com.ali.me.cost.heuristic.impl;

import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;

public class DragonGlassesHeuristic implements Heuristic {
    @Override
    public int heuristicCost(State state) {
        TheStateThatKnowsNothing.NorthOfTheWall[][] grid = ((TheStateThatKnowsNothing) state).getGrid();
        int numberOfWhiteWalkers = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == TheStateThatKnowsNothing.NorthOfTheWall.WHITE_WALKER) numberOfWhiteWalkers++;
        return (numberOfWhiteWalkers + 3 - 1) / 3;
    }
}
