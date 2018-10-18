package com.ali.me;

import java.util.*;

import com.ali.me.cost.heuristic.impl.DragonGlassesHeuristic;
import com.ali.me.cost.heuristic.impl.NearestNextObjectiveHeuristic;
import com.ali.me.problem.Problem;
import com.ali.me.problem.impl.SaveWesterosProblem;
import com.ali.me.search.GeneralSearch;
import com.ali.me.search.impl.*;
import com.ali.me.state.State;
import com.ali.me.state.impl.SaveWesterosState;
import com.ali.me.state.impl.SaveWesterosState.NorthOfTheWall;
import com.ali.me.view.SolutionViewer;

public class Main {

    /**
     * Generates a Problem with random configurations.
     *
     * @return A Problem with random configurations.
     */
    private static Problem genGrid() {
        /* Un-comment one of these to test a specific configuration or even write your own. Do not forget
         to comment out the random generation below. */

//		SaveWesterosProblem problem = new SaveWesterosProblem(30, 30, 20, 100, 100);
//        SaveWesterosProblem problem = new SaveWesterosProblem(20, 20, 20, 100, 5);
//		SaveWesterosProblem problem = new SaveWesterosProblem(10, 10, 20, 20, 5);
//        SaveWesterosProblem problem = new SaveWesterosProblem(7, 7, 10, 10, 2);
//        SaveWesterosProblem problem = new SaveWesterosProblem(7, 7, 10, 12, 2);
//        SaveWesterosProblem problem = new SaveWesterosProblem(7, 8, 6, 13, 5);
//        SaveWesterosProblem problem = new SaveWesterosProblem(6, 7, 5, 11, 2);
//        SaveWesterosProblem problem = new SaveWesterosProblem(9, 8, 8, 15, 10);
//        SaveWesterosProblem problem = new SaveWesterosProblem(9, 9, 10, 15, 15);
//        SaveWesterosProblem problem = new SaveWesterosProblem(5, 5, 5, 5, 3);
//        SaveWesterosProblem problem = new SaveWesterosProblem(4, 4, 1, 5, 0});
//        SaveWesterosProblem problem = new SaveWesterosProblem(8, 8, 10, 15, 5);

         /* If you want to test something specific just comment these lines and uncomment one of the lines above
         or even write your own. */
        Random random = new Random();
        int numberOfRows = random.nextInt(5) + 4;
        int numberOfColumns = random.nextInt(5) + 4;
        int numberOfDragonGlasses = random.nextInt(10);
        int numberOfWhiteWalkers = random.nextInt(10);
        int numberOfObstacles = random.nextInt(4);
        Problem problem = new SaveWesterosProblem(numberOfRows, numberOfColumns, numberOfDragonGlasses, numberOfWhiteWalkers, numberOfObstacles);

        NorthOfTheWall[][] grid = ((SaveWesterosState) problem.getInitialState()).getGrid();
        System.err.println("Initial Grid");
        for (NorthOfTheWall[] row : grid) System.err.println(Arrays.toString(row));
        return problem;
    }

    /**
     * Searches for an Optimal Solution given a Problem, a desired Strategy.
     * If visualize is set to True a GUI will show the solution (if one was found).
     *
     * @param problem   The Problem to search for a solution for.
     * @param strategy  The desired strategy.
     * @param visualize Whether to visualize the solution (if one was found).
     */
    private static void search(Problem problem, String strategy, boolean visualize) {
        strategy = strategy.toLowerCase();
        State goalState = null;
        if (strategy.equals("df")) goalState = GeneralSearch.search(problem, new DFSSearchStrategy());
        else if (strategy.equals("bf")) goalState = GeneralSearch.search(problem, new BFSSearchStrategy());
        else if (strategy.equals("id")) {
            long numberOfExpandedNodes = 0;
            for (int depthLimit = 0; depthLimit <= 10000 && goalState == null; depthLimit++) {
                goalState = GeneralSearch.search(problem, new IterativeDeepeningSearchStrategy(depthLimit));
                numberOfExpandedNodes += GeneralSearch.expandedNodes;
            }
            GeneralSearch.expandedNodes = numberOfExpandedNodes;
        } else if (strategy.equals("uc")) goalState = GeneralSearch.search(problem, new UCSSearchStrategy());
        else if (strategy.equals("gr1"))
            goalState = GeneralSearch.search(problem, new GBFSSearchStrategy(new DragonGlassesHeuristic()));
        else if (strategy.equals("gr2"))
            goalState = GeneralSearch.search(problem, new GBFSSearchStrategy(new NearestNextObjectiveHeuristic()));
        else if (strategy.equals("as1"))
            goalState = GeneralSearch.search(problem, new AStarSearchStrategy(new DragonGlassesHeuristic()));
        else if (strategy.equals("as2"))
            goalState = GeneralSearch.search(problem, new AStarSearchStrategy(new NearestNextObjectiveHeuristic()));
        else {
            System.err.println("Invalid Search Strategy. Valid Search Strategies are 'df', 'bf', 'id', 'uc', 'gr1', 'gr2', 'as1', 'as2'");
            return;
        }
        if (goalState == null)
            System.err.printf("%s Found No Solution\n", strategy);
        else {
            List<State> states = getPath(goalState);
            List<String> moves = new ArrayList<>();
            for (State state : states)
                if (state.getAction() != null) moves.add(state.getAction().getActionString());
            System.err.printf("%s Found a solution, set 'visualize' to true to check it, " +
                    "Sequence Of Move=%s, cost = %s, expanded %s nodes\n", strategy, moves, goalState.getPathCost(), GeneralSearch.expandedNodes);
            if (visualize) {
                SolutionViewer sv = new SolutionViewer(strategy, states);
            }
        }
    }

    /**
     * Returns a list of states that are resulted from the agent
     * taking actions to reach the Goal state. The first state in
     * the list is the Initial state and the last one is the Goal state.
     *
     * @param state the Goal state
     * @return List of States
     */
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

    /**
     * Mini-driver to run and test the program. This will
     * generate a problem and run the given Search Strategy on it.
     *
     * @param strategy The desired strategy should be any of [df, bf, id, uc, gr1, gr2, as1, as2]
     */
    private static void go(String strategy) {
        System.err.printf("Running %s\n", strategy);
        try {
            Problem problem = genGrid();
            long start = System.currentTimeMillis();
            search(problem, strategy, true);
            long end = System.currentTimeMillis();
            System.err.printf("Search Took %s seconds\n", (end - start) / 1000.0);
        } catch (OutOfMemoryError oom) {
            oom.printStackTrace();
            System.err.printf("STRATEGY %s RAN OUT OF MEMORY\n", strategy.toUpperCase());
        }
        System.err.println("==============================================");
    }

    /**
     * Main method for generating a Problem and running
     * a Search Strategy on it. NOTE: THIS WILL GENERATE
     * A DIFFERENT PROBLEM FOR EACH CALL.
     */
    public static void main(String[] args) {
        new Thread(null, () -> {
            go("df");
            go("bf");
            go("id");
            go("uc");
            go("gr1");
            go("gr2");
            go("as1");
            go("as2");
        }, "Increase Stack Size", 1 << 27).start();
    }
}
