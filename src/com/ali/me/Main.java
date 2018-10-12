package com.ali.me;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.ali.me.cost.heuristic.impl.DragonGlassesHeuristic;
import com.ali.me.problem.Problem;
import com.ali.me.problem.impl.SaveWestrosProblem;
import com.ali.me.search.impl.*;
import com.ali.me.state.State;
import com.ali.me.state.impl.SaveWestrosState;
import com.ali.me.state.impl.SaveWestrosState.NorthOfTheWall;
import com.ali.me.view.SolutionViewer;

public class Main {

    private static Problem genGrid() {
//		SaveWestrosProblem problem = new SaveWestrosProblem(30, 30, 20, 100, 100);
//        SaveWestrosProblem problem = new SaveWestrosProblem(20, 20, 20, 100, 5);
//		SaveWestrosProblem problem = new SaveWestrosProblem(10, 10, 20, 20, 5);
//        SaveWestrosProblem problem = new SaveWestrosProblem(7, 7, 10, 10, 2);
//        SaveWestrosProblem problem = new SaveWestrosProblem(7, 7, 10, 12, 2);
//        SaveWestrosProblem problem = new SaveWestrosProblem(7, 8, 10, 12, 2);
        SaveWestrosProblem problem = new SaveWestrosProblem(5, 5, 5, 5, 3);
//        SaveWestrosProblem problem = new SaveWestrosProblem(4, 4, 1, 5, 0});
//        SaveWestrosProblem problem = new SaveWestrosProblem(8, 8, 10, 15, 5);

        NorthOfTheWall[][] grid = ((SaveWestrosState) problem.getInitialState()).getGrid();
        System.err.println("Initial Grid");
        for (int i = 0; i < grid.length; i++)
            System.err.println(Arrays.toString(grid[i]));
        return problem;
    }

    private static void search(Problem problem, String strategy, boolean visualize) {
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
        if (strategy.equals("id")) {
            IterativeDeepeningSearchStrategy id = new IterativeDeepeningSearchStrategy();
            goalState = id.search(problem);
        }
        if (strategy.equals("ucs")) {
            UCSSearchStrategy ucs = new UCSSearchStrategy();
            goalState = ucs.search(problem);
        }
        if (strategy.equals("gbfsh1")) {
            GBFSSearchStrategy gbfs = new GBFSSearchStrategy(new DragonGlassesHeuristic());
            goalState = gbfs.search(problem);
        }
        if (strategy.equals("astarh1")) {
            AStarSearchStrategy astar = new AStarSearchStrategy(new DragonGlassesHeuristic());
            goalState = astar.search(problem);
        }
        if (goalState == null)
            System.err.println("Found No Solution");
        else {
            if (visualize) {
                SolutionViewer sv = new SolutionViewer("Solution Viewer", getPath(goalState));
            }
        }
    }

    private static List<State> getPath(State state) {
        Stack<State> stack = new Stack<>();

        while (state != null) {
            stack.push(state);
            state = state.getParent();
        }
        List<State> path = new ArrayList<>();
        while (!stack.isEmpty()) path.add(stack.pop());
        return path;
    }

    public static void main(String[] args) {
        new Thread(null, () -> {
            Problem problem = genGrid();
            long start = System.currentTimeMillis();
            search(problem, "astarh1", true);
            long end = System.currentTimeMillis();
            System.err.println((end - start) / 1000.0);
        }, "Increase Stack Size", 1 << 27).start();
    }
}
