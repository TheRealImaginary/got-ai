package com.ali.me;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.ali.me.cost.heuristic.impl.DragonGlassesHeuristic;
import com.ali.me.problem.Problem;
import com.ali.me.problem.impl.SaveWesterosProblem;
import com.ali.me.search.GeneralSearch;
import com.ali.me.search.impl.*;
import com.ali.me.state.State;
import com.ali.me.state.impl.SaveWesterosState;
import com.ali.me.state.impl.SaveWesterosState.NorthOfTheWall;
import com.ali.me.view.SolutionViewer;

public class Main {

    private static Problem genGrid() {
//		SaveWesterosProblem problem = new SaveWesterosProblem(30, 30, 20, 100, 100);
//        SaveWesterosProblem problem = new SaveWesterosProblem(20, 20, 20, 100, 5);
//		SaveWesterosProblem problem = new SaveWesterosProblem(10, 10, 20, 20, 5);
//        SaveWesterosProblem problem = new SaveWesterosProblem(7, 7, 10, 10, 2);
//        SaveWesterosProblem problem = new SaveWesterosProblem(7, 7, 10, 12, 2);
//        SaveWesterosProblem problem = new SaveWesterosProblem(7, 8, 10, 12, 2);
        SaveWesterosProblem problem = new SaveWesterosProblem(5, 5, 5, 5, 3);
//        SaveWesterosProblem problem = new SaveWesterosProblem(4, 4, 1, 5, 0});
//        SaveWesterosProblem problem = new SaveWesterosProblem(8, 8, 10, 15, 5);

        NorthOfTheWall[][] grid = ((SaveWesterosState) problem.getInitialState()).getGrid();
        System.err.println("Initial Grid");
        for (int i = 0; i < grid.length; i++)
            System.err.println(Arrays.toString(grid[i]));
        return problem;
    }

    private static void search(Problem problem, String strategy, boolean visualize) {
        strategy = strategy.toLowerCase();
        State goalState = null;
        if (strategy.equals("df")) goalState = GeneralSearch.search(problem, new DFSSearchStrategy());
        if (strategy.equals("bf")) goalState = GeneralSearch.search(problem, new BFSSearchStrategy());
        if (strategy.equals("id")) {
            for (int depthLimit = 0; depthLimit <= 10000 && goalState == null; depthLimit++)
                goalState = GeneralSearch.search(problem, new IterativeDeepeningSearchStrategy(depthLimit));
        }
        if (strategy.equals("uc")) goalState = GeneralSearch.search(problem, new UCSSearchStrategy());
        if (strategy.equals("gr1"))
            goalState = GeneralSearch.search(problem, new GBFSSearchStrategy(new DragonGlassesHeuristic()));
        if (strategy.equals("as1"))
            goalState = GeneralSearch.search(problem, new AStarSearchStrategy(new DragonGlassesHeuristic()));
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
            search(problem, "df", true);
            long end = System.currentTimeMillis();
            System.err.println(String.format(String.format("Search Took %s", (end - start) / 1000.0)));
        }, "Increase Stack Size", 1 << 27).start();
    }
}
