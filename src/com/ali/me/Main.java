package com.ali.me;

import java.util.Arrays;

import com.ali.me.cost.heuristic.impl.DragonGlassesHeuristic;
import com.ali.me.problem.Problem;
import com.ali.me.problem.impl.PlaceholderProblem;
import com.ali.me.search.impl.BFSSearchStrategy;
import com.ali.me.search.impl.DFSSearchStrategy;
import com.ali.me.search.impl.GBFSSearchStrategy;
import com.ali.me.search.impl.UCSSearchStrategy;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;
import com.ali.me.state.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class Main {

    public static Problem genGrid() {
        PlaceholderProblem problem = new PlaceholderProblem(20, 20, 20, 100, 5);
//        PlaceholderProblem problem = new PlaceholderProblem(5, 5, 4, 6, 1);
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
        if (strategy.equals("ucs")) {
            UCSSearchStrategy ucs = new UCSSearchStrategy();
            goalState = ucs.search(problem);
        }
        if (strategy.equals("gbfs")) {
            GBFSSearchStrategy gbfs = new GBFSSearchStrategy(new DragonGlassesHeuristic());
            goalState = gbfs.search(problem);
        }
        if (goalState == null) System.err.println("Found No Solution");
        else {
            TheStateThatKnowsNothing nothing = (TheStateThatKnowsNothing) goalState;
            System.err.println("Goal state");
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
                search(problem, "gbfs", false);
            }
        }, "Increase Stack Size", 1 << 27).start();
    }
}
