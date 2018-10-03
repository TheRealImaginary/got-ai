package com.ali.me.problem.impl;

import com.ali.me.cost.action.ActionCost;
import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.problem.Problem;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;
import com.ali.me.state.impl.TheStateThatKnowsNothing.NorthOfTheWall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlaceholderProblem extends Problem {

    public enum Action {
        MOVE,
        PICK_UP,
        ATTACK
    }

    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfDragonGlasses;

    public PlaceholderProblem(int numberOfRows, int numberOfColumns, int numberOfDragonGlasses, int numberOfWhiteWalkers, int numberOfObstacles) {
        NorthOfTheWall[][] initialGrid = new NorthOfTheWall[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++)
            Arrays.fill(initialGrid[row], NorthOfTheWall.E);

        // Place Jon
        initialGrid[numberOfRows - 1][numberOfColumns - 1] = NorthOfTheWall.J;

        // For consistency
        Random random = new Random(123);
        // Place White Walkers
        for (int i = 1; i <= numberOfWhiteWalkers; ) {
            int row = random.nextInt(numberOfRows);
            int col = random.nextInt(numberOfColumns);
            if (initialGrid[row][col] == NorthOfTheWall.E) {
                initialGrid[row][col] = NorthOfTheWall.W;
                i++;
            }
        }

        // Place Obstacles
        for (int i = 1; i <= numberOfObstacles; ) {
            int row = random.nextInt(numberOfRows);
            int col = random.nextInt(numberOfColumns);
            if (initialGrid[row][col] == NorthOfTheWall.E) {
                initialGrid[row][col] = NorthOfTheWall.O;
                i++;
            }
        }

        // Place Dragon Stone
        while (true) {
            int row = random.nextInt(numberOfRows);
            int col = random.nextInt(numberOfColumns);
            if (initialGrid[row][col] == NorthOfTheWall.E) {
                initialGrid[row][col] = NorthOfTheWall.D;
                break;
            }
        }

        this.initialState = new TheStateThatKnowsNothing(numberOfRows - 1, numberOfColumns - 1, 0, 0, 0, 0, initialGrid, null);
        this.numberOfDragonGlasses = numberOfDragonGlasses;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public State getInitialState() {
        return this.initialState;
    }

    @Override
    public List<State> expand(State state, ActionCost actionCost, Heuristic heuristic) {
        ArrayList<State> nextStates = new ArrayList<>();
        State a = pickUpDragonGlass(state, actionCost, heuristic);
        if (a != null)
            nextStates.add(a);
        a = moveLeft(state, actionCost, heuristic);
        if (a != null)
            nextStates.add(a);
        a = moveRight(state, actionCost, heuristic);
        if (a != null)
            nextStates.add(a);
        a = moveUp(state, actionCost, heuristic);
        if (a != null)
            nextStates.add(a);
        a = moveDown(state, actionCost, heuristic);
        if (a != null)
            nextStates.add(a);
        a = attackWhiteWalker(state, actionCost, heuristic);
        if (a != null)
            nextStates.add(a);
        return nextStates;
    }

    @Override
    public boolean isGoal(State state) {
        NorthOfTheWall[][] grid = ((TheStateThatKnowsNothing) state).getGrid();
        boolean isEmpty = true;
        for (int row = 0; row < this.numberOfRows; row++)
            for (int col = 0; col < this.numberOfColumns; col++)
                if (grid[row][col] == NorthOfTheWall.W)
                    isEmpty = false;
        return isEmpty;
    }

    private State moveLeft(State state, ActionCost actionCost, Heuristic heuristic) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        int newCol = col - 1;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (col > 0 && (grid[row][newCol] == NorthOfTheWall.E || grid[row][newCol] == NorthOfTheWall.D)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.J)
                newGrid[row][col] = NorthOfTheWall.E;
            if (newGrid[row][newCol] == NorthOfTheWall.E)
                newGrid[row][newCol] = NorthOfTheWall.J;
            TheStateThatKnowsNothing nextState = new TheStateThatKnowsNothing(row, newCol, theStateThatKnowsNothing.getDragonGlasses(), theStateThatKnowsNothing.getPathCost() + actionCost.getActionCost(Action.MOVE), 0, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State moveRight(State state, ActionCost actionCost, Heuristic heuristic) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        int newCol = col + 1;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (col < this.numberOfColumns - 1 && (grid[row][newCol] == NorthOfTheWall.E || grid[row][newCol] == NorthOfTheWall.D)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.J)
                newGrid[row][col] = NorthOfTheWall.E;
            if (newGrid[row][newCol] == NorthOfTheWall.E)
                newGrid[row][newCol] = NorthOfTheWall.J;
            TheStateThatKnowsNothing nextState = new TheStateThatKnowsNothing(row, newCol, theStateThatKnowsNothing.getDragonGlasses(), theStateThatKnowsNothing.getPathCost() + actionCost.getActionCost(Action.MOVE), 0, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State moveUp(State state, ActionCost actionCost, Heuristic heuristic) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        int newRow = row - 1;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (row > 0 && (grid[newRow][col] == NorthOfTheWall.E || grid[newRow][col] == NorthOfTheWall.D)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.J)
                newGrid[row][col] = NorthOfTheWall.E;
            if (newGrid[newRow][col] == NorthOfTheWall.E)
                newGrid[newRow][col] = NorthOfTheWall.J;
            TheStateThatKnowsNothing nextState = new TheStateThatKnowsNothing(newRow, col, theStateThatKnowsNothing.getDragonGlasses(), theStateThatKnowsNothing.getPathCost() + actionCost.getActionCost(Action.MOVE), 0, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State moveDown(State state, ActionCost actionCost, Heuristic heuristic) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        int newRow = row + 1;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (row < this.numberOfRows - 1 && (grid[newRow][col] == NorthOfTheWall.E || grid[newRow][col] == NorthOfTheWall.D)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.J)
                newGrid[row][col] = NorthOfTheWall.E;
            if (newGrid[newRow][col] == NorthOfTheWall.E)
                newGrid[newRow][col] = NorthOfTheWall.J;
            TheStateThatKnowsNothing nextState = new TheStateThatKnowsNothing(newRow, col, theStateThatKnowsNothing.getDragonGlasses(), theStateThatKnowsNothing.getPathCost() + actionCost.getActionCost(Action.MOVE), 0, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State attackWhiteWalker(State state, ActionCost actionCost, Heuristic heuristic) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int attacked = 0;
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int numberOfDragonGlasses = theStateThatKnowsNothing.getDragonGlasses();
        if (numberOfDragonGlasses == 0)
            return null;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        NorthOfTheWall[][] newGrid = copyGrid(grid);
        for (int i = 0; i < dx.length; i++) {
            int r = row + dx[i], c = col + dy[i];
            if (r >= 0 && r < this.numberOfRows && c >= 0 && c < this.numberOfColumns && grid[r][c] == NorthOfTheWall.W) {
                attacked++;
                newGrid[r][c] = NorthOfTheWall.E;
            }
        }
        if (attacked != 0) {
            TheStateThatKnowsNothing nextState = new TheStateThatKnowsNothing(row, col, numberOfDragonGlasses - 1, theStateThatKnowsNothing.getPathCost() + actionCost.getActionCost(Action.ATTACK), 0, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State pickUpDragonGlass(State state, ActionCost actionCost, Heuristic heuristic) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        if (theStateThatKnowsNothing.getDragonGlasses() == this.numberOfDragonGlasses) return null;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (grid[row][col] == NorthOfTheWall.D) {
            TheStateThatKnowsNothing nextState = new TheStateThatKnowsNothing(row, col, this.numberOfDragonGlasses, theStateThatKnowsNothing.getPathCost() + actionCost.getActionCost(Action.PICK_UP), 0, theStateThatKnowsNothing.getDepth() + 1, grid, state);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    public static NorthOfTheWall[][] copyGrid(NorthOfTheWall[][] grid) {
        NorthOfTheWall[][] gridOut = new NorthOfTheWall[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                gridOut[i][j] = grid[i][j];
        return gridOut;
    }
}