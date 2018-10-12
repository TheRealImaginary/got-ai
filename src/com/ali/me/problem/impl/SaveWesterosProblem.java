package com.ali.me.problem.impl;

import com.ali.me.action.impl.SaveWesterosAction;
import com.ali.me.cost.action.ActionCost;
import com.ali.me.cost.action.impl.SaveWesterosActionCost;
import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.cost.heuristic.impl.ZeroHeuristic;
import com.ali.me.problem.Problem;
import com.ali.me.state.State;
import com.ali.me.state.impl.SaveWesterosState;
import com.ali.me.state.impl.SaveWesterosState.NorthOfTheWall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SaveWesterosProblem extends Problem {

    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfDragonGlasses;

    public SaveWesterosProblem(int numberOfRows, int numberOfColumns, int numberOfDragonGlasses, int numberOfWhiteWalkers, int numberOfObstacles) {
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

//        this.initialState = new SaveWesterosState(numberOfRows - 1, numberOfColumns - 1, 0, 0, 0, 0, initialGrid, null);
        this.initialState = new SaveWesterosState(0, null, 0, null, 0, numberOfRows - 1, numberOfColumns - 1, 0, initialGrid);
        this.numberOfDragonGlasses = numberOfDragonGlasses;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    @Override
    public List<State> expand(State state) {
        return expand(state, new SaveWesterosActionCost(), new ZeroHeuristic());
    }

    @Override
    public List<State> expand(State state, Heuristic heuristic) {
        return expand(state, new SaveWesterosActionCost(), heuristic);
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
        NorthOfTheWall[][] grid = ((SaveWesterosState) state).getGrid();
        boolean isEmpty = true;
        for (int row = 0; row < this.numberOfRows; row++)
            for (int col = 0; col < this.numberOfColumns; col++)
                if (grid[row][col] == NorthOfTheWall.W)
                    isEmpty = false;
        return isEmpty;
    }

    private State moveLeft(State state, ActionCost actionCost, Heuristic heuristic) {
        SaveWesterosState saveWesterosState = (SaveWesterosState) state;
        int row = saveWesterosState.getRow();
        int col = saveWesterosState.getColumn();
        int newCol = col - 1;
        NorthOfTheWall[][] grid = saveWesterosState.getGrid();
        if (col > 0 && (grid[row][newCol] == NorthOfTheWall.E || grid[row][newCol] == NorthOfTheWall.D)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.J)
                newGrid[row][col] = NorthOfTheWall.E;
            if (newGrid[row][newCol] == NorthOfTheWall.E)
                newGrid[row][newCol] = NorthOfTheWall.J;
//            SaveWesterosState nextState = new SaveWesterosState(row, newCol, saveWesterosState.getDragonGlasses(), saveWesterosState.getPathCost() + actionCost.getActionCost(Action.MOVE), 0, saveWesterosState.getDepth() + 1, newGrid, state);
            SaveWesterosState nextState = new SaveWesterosState(state.getDepth() + 1, state, state.getPathCost() + actionCost.getActionCost(new SaveWesterosAction(SaveWesterosAction.MOVE_LEFT)), new SaveWesterosAction(SaveWesterosAction.MOVE_LEFT), 0, row, newCol, saveWesterosState.getDragonGlasses(), newGrid);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State moveRight(State state, ActionCost actionCost, Heuristic heuristic) {
        SaveWesterosState saveWesterosState = (SaveWesterosState) state;
        int row = saveWesterosState.getRow();
        int col = saveWesterosState.getColumn();
        int newCol = col + 1;
        NorthOfTheWall[][] grid = saveWesterosState.getGrid();
        if (col < this.numberOfColumns - 1 && (grid[row][newCol] == NorthOfTheWall.E || grid[row][newCol] == NorthOfTheWall.D)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.J)
                newGrid[row][col] = NorthOfTheWall.E;
            if (newGrid[row][newCol] == NorthOfTheWall.E)
                newGrid[row][newCol] = NorthOfTheWall.J;
//            SaveWesterosState nextState = new SaveWesterosState(row, newCol, saveWesterosState.getDragonGlasses(), saveWesterosState.getPathCost() + actionCost.getActionCost(Action.MOVE), 0, saveWesterosState.getDepth() + 1, newGrid, state);
            SaveWesterosState nextState = new SaveWesterosState(state.getDepth() + 1, state, state.getPathCost() + actionCost.getActionCost(new SaveWesterosAction(SaveWesterosAction.MOVE_RIGHT)), new SaveWesterosAction(SaveWesterosAction.MOVE_RIGHT), 0, row, newCol, saveWesterosState.getDragonGlasses(), newGrid);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State moveUp(State state, ActionCost actionCost, Heuristic heuristic) {
        SaveWesterosState saveWesterosState = (SaveWesterosState) state;
        int row = saveWesterosState.getRow();
        int col = saveWesterosState.getColumn();
        int newRow = row - 1;
        NorthOfTheWall[][] grid = saveWesterosState.getGrid();
        if (row > 0 && (grid[newRow][col] == NorthOfTheWall.E || grid[newRow][col] == NorthOfTheWall.D)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.J)
                newGrid[row][col] = NorthOfTheWall.E;
            if (newGrid[newRow][col] == NorthOfTheWall.E)
                newGrid[newRow][col] = NorthOfTheWall.J;
//            SaveWesterosState nextState = new SaveWesterosState(newRow, col, saveWesterosState.getDragonGlasses(), saveWesterosState.getPathCost() + actionCost.getActionCost(Action.MOVE), 0, saveWesterosState.getDepth() + 1, newGrid, state);
            SaveWesterosState nextState = new SaveWesterosState(state.getDepth() + 1, state, state.getPathCost() + actionCost.getActionCost(new SaveWesterosAction(SaveWesterosAction.MOVE_UP)), new SaveWesterosAction(SaveWesterosAction.MOVE_UP), 0, newRow, col, saveWesterosState.getDragonGlasses(), newGrid);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State moveDown(State state, ActionCost actionCost, Heuristic heuristic) {
        SaveWesterosState saveWesterosState = (SaveWesterosState) state;
        int row = saveWesterosState.getRow();
        int col = saveWesterosState.getColumn();
        int newRow = row + 1;
        NorthOfTheWall[][] grid = saveWesterosState.getGrid();
        if (row < this.numberOfRows - 1 && (grid[newRow][col] == NorthOfTheWall.E || grid[newRow][col] == NorthOfTheWall.D)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.J)
                newGrid[row][col] = NorthOfTheWall.E;
            if (newGrid[newRow][col] == NorthOfTheWall.E)
                newGrid[newRow][col] = NorthOfTheWall.J;
//            SaveWesterosState nextState = new SaveWesterosState(newRow, col, saveWesterosState.getDragonGlasses(), saveWesterosState.getPathCost() + actionCost.getActionCost(Action.MOVE), 0, saveWesterosState.getDepth() + 1, newGrid, state);
            SaveWesterosState nextState = new SaveWesterosState(state.getDepth() + 1, state, state.getPathCost() + actionCost.getActionCost(new SaveWesterosAction(SaveWesterosAction.MOVE_DOWN)), new SaveWesterosAction(SaveWesterosAction.MOVE_DOWN), 0, newRow, col, saveWesterosState.getDragonGlasses(), newGrid);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State attackWhiteWalker(State state, ActionCost actionCost, Heuristic heuristic) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int attacked = 0;
        SaveWesterosState saveWesterosState = (SaveWesterosState) state;
        int numberOfDragonGlasses = saveWesterosState.getDragonGlasses();
        if (numberOfDragonGlasses == 0)
            return null;
        int row = saveWesterosState.getRow();
        int col = saveWesterosState.getColumn();
        NorthOfTheWall[][] grid = saveWesterosState.getGrid();
        NorthOfTheWall[][] newGrid = copyGrid(grid);
        for (int i = 0; i < dx.length; i++) {
            int r = row + dx[i], c = col + dy[i];
            if (r >= 0 && r < this.numberOfRows && c >= 0 && c < this.numberOfColumns && grid[r][c] == NorthOfTheWall.W) {
                attacked++;
                newGrid[r][c] = NorthOfTheWall.E;
            }
        }
        if (attacked != 0) {
//            SaveWesterosState nextState = new SaveWesterosState(row, col, numberOfDragonGlasses - 1, saveWesterosState.getPathCost() + actionCost.getActionCost(Action.ATTACK), 0, saveWesterosState.getDepth() + 1, newGrid, state);
            SaveWesterosState nextState = new SaveWesterosState(state.getDepth() + 1, state, state.getPathCost() + actionCost.getActionCost(new SaveWesterosAction(SaveWesterosAction.ATTACK)), new SaveWesterosAction(SaveWesterosAction.ATTACK), 0, row, col, numberOfDragonGlasses - 1, newGrid);
            nextState.setHeuristicCost(heuristic.heuristicCost(nextState));
            return nextState;
        }
        return null;
    }

    private State pickUpDragonGlass(State state, ActionCost actionCost, Heuristic heuristic) {
        SaveWesterosState saveWesterosState = (SaveWesterosState) state;
        int row = saveWesterosState.getRow();
        int col = saveWesterosState.getColumn();
        if (saveWesterosState.getDragonGlasses() == this.numberOfDragonGlasses) return null;
        NorthOfTheWall[][] grid = saveWesterosState.getGrid();
        if (grid[row][col] == NorthOfTheWall.D) {
//            SaveWesterosState nextState = new SaveWesterosState(row, col, this.numberOfDragonGlasses, saveWesterosState.getPathCost() + actionCost.getActionCost(Action.PICK_UP), 0, saveWesterosState.getDepth() + 1, grid, state);
            SaveWesterosState nextState = new SaveWesterosState(state.getDepth() + 1, state, state.getPathCost() + actionCost.getActionCost(new SaveWesterosAction(SaveWesterosAction.PICK_UP)), new SaveWesterosAction(SaveWesterosAction.PICK_UP), 0, row, col, numberOfDragonGlasses - 1, grid);
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
