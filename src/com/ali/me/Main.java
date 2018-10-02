package com.ali.me;

import java.util.Arrays;

import com.ali.me.Problem.Problem;
import com.ali.me.Problem.impl.PlaceholderProblem;
import com.ali.me.Search.impl.BFSSearchStrategy;
import com.ali.me.Search.impl.DFSSearchStrategy;
import com.ali.me.State.State;
import com.ali.me.State.impl.TheStateThatKnowsNothing;
import com.ali.me.State.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class Main {

    public static Problem genGrid() {
        PlaceholderProblem problem = new PlaceholderProblem(20, 20, 20, 100, 5);
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
        if (strategy.equals("bfs")) {
            BFSSearchStrategy bfs = new BFSSearchStrategy();
            goalState = bfs.search(problem);
        }
        if (goalState == null) System.err.println("Found No Solution");
        else {
            TheStateThatKnowsNothing nothing = (TheStateThatKnowsNothing) goalState;
            System.err.println("Goal State");
            System.err.println(nothing);
            NorthOfTheWall[][] grid = nothing.getGrid();
            for (int i = 0; i < grid.length; i++)
                System.err.println(Arrays.toString(grid[i]));
        }
    }


    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            @Override
            public void run() {
                Problem problem = genGrid();
                search(problem, "bfs", false);
            }
        }, "Increased Stack Size", 1 << 27).start();
    }
}
