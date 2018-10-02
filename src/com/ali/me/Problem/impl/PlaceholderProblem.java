package com.ali.me.Problem.impl;

import com.ali.me.Problem.Problem;
import com.ali.me.State.State;
import com.ali.me.State.impl.TheStateThatKnowsNothing;
import com.ali.me.State.impl.TheStateThatKnowsNothing.NorthOfTheWall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlaceholderProblem extends Problem {

    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfDragonGlasses;

    public PlaceholderProblem(int numberOfRows, int numberOfColumns, int numberOfDragonGlasses, int numberOfWhiteWalkers, int numberOfObstacles) {
        NorthOfTheWall[][] initialGrid = new NorthOfTheWall[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++)
            Arrays.fill(initialGrid[row], NorthOfTheWall.EMPTY);

        // Place Jon
        initialGrid[numberOfRows - 1][numberOfColumns - 1] = NorthOfTheWall.JON;

        // For consistency
        Random random = new Random(123);
        // Place White Walkers
        for (int i = 1; i <= numberOfWhiteWalkers; ) {
            int row = random.nextInt(numberOfRows);
            int col = random.nextInt(numberOfColumns);
            if (initialGrid[row][col] == NorthOfTheWall.EMPTY) {
                initialGrid[row][col] = NorthOfTheWall.WHITE_WALKER;
                i++;
            }
        }

        // Place Obstacles
        for (int i = 1; i <= numberOfObstacles; ) {
            int row = random.nextInt(numberOfRows);
            int col = random.nextInt(numberOfColumns);
            if (initialGrid[row][col] == NorthOfTheWall.EMPTY) {
                initialGrid[row][col] = NorthOfTheWall.OBSTACLE;
                i++;
            }
        }

        // Place Dragon Stone
        while (true) {
            int row = random.nextInt(numberOfRows);
            int col = random.nextInt(numberOfColumns);
            if (initialGrid[row][col] == NorthOfTheWall.EMPTY) {
                initialGrid[row][col] = NorthOfTheWall.DRAGON_STONE;
                break;
            }
        }

        this.initialState = new TheStateThatKnowsNothing(numberOfRows - 1, numberOfColumns - 1, 0, 0, 0, initialGrid, null);
        this.numberOfDragonGlasses = numberOfDragonGlasses;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public State getInitialState() {
        return this.initialState;
    }

    @Override
    public List<State> expand(State state) {
        ArrayList<State> nextStates = new ArrayList<>();
        State a = pickUpDragonGlass(state);
        if (a != null)
            nextStates.add(a);
        a = moveLeft(state);
        if (a != null)
            nextStates.add(a);
        a = moveRight(state);
        if (a != null)
            nextStates.add(a);
        a = moveUp(state);
        if (a != null)
            nextStates.add(a);
        a = moveDown(state);
        if (a != null)
            nextStates.add(a);
        a = attackWhiteWalker(state);
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
                if (grid[row][col] == NorthOfTheWall.WHITE_WALKER)
                    isEmpty = false;
        return isEmpty;
    }

    private State moveLeft(State state) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        int newCol = col - 1;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (col > 0 && (grid[row][newCol] == NorthOfTheWall.EMPTY || grid[row][newCol] == NorthOfTheWall.DRAGON_STONE)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.JON)
                newGrid[row][col] = NorthOfTheWall.EMPTY;
            if (newGrid[row][newCol] == NorthOfTheWall.EMPTY)
                newGrid[row][newCol] = NorthOfTheWall.JON;

            // Action Cost is 0
            return new TheStateThatKnowsNothing(row, newCol, theStateThatKnowsNothing.getDragonGlasses(), theStateThatKnowsNothing.getCost() + 1, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
        }
        return null;
    }

    private State moveRight(State state) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        int newCol = col + 1;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (col < this.numberOfColumns - 1 && (grid[row][newCol] == NorthOfTheWall.EMPTY || grid[row][newCol] == NorthOfTheWall.DRAGON_STONE)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.JON)
                newGrid[row][col] = NorthOfTheWall.EMPTY;
            if (newGrid[row][newCol] == NorthOfTheWall.EMPTY)
                newGrid[row][newCol] = NorthOfTheWall.JON;
            // Action Cost is 0
            return new TheStateThatKnowsNothing(row, newCol, theStateThatKnowsNothing.getDragonGlasses(), theStateThatKnowsNothing.getCost() + 1, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
        }
        return null;
    }

    private State moveUp(State state) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        int newRow = row - 1;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (row > 0 && (grid[newRow][col] == NorthOfTheWall.EMPTY || grid[newRow][col] == NorthOfTheWall.DRAGON_STONE)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.JON)
                newGrid[row][col] = NorthOfTheWall.EMPTY;
            if (newGrid[newRow][col] == NorthOfTheWall.EMPTY)
                newGrid[newRow][col] = NorthOfTheWall.JON;
            // Action Cost is 0
            return new TheStateThatKnowsNothing(newRow, col, theStateThatKnowsNothing.getDragonGlasses(), theStateThatKnowsNothing.getCost() + 1, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
        }
        return null;
    }

    private State moveDown(State state) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        int newRow = row + 1;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (row < this.numberOfRows - 1 && (grid[newRow][col] == NorthOfTheWall.EMPTY || grid[newRow][col] == NorthOfTheWall.DRAGON_STONE)) {
            // Move Jon
            NorthOfTheWall[][] newGrid = copyGrid(grid);
            if (newGrid[row][col] == NorthOfTheWall.JON)
                newGrid[row][col] = NorthOfTheWall.EMPTY;
            if (newGrid[newRow][col] == NorthOfTheWall.EMPTY)
                newGrid[newRow][col] = NorthOfTheWall.JON;
            // Action Cost is 0
            return new TheStateThatKnowsNothing(newRow, col, theStateThatKnowsNothing.getDragonGlasses(), theStateThatKnowsNothing.getCost() + 1, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
        }
        return null;
    }

    private State attackWhiteWalker(State state) {
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
            if (r >= 0 && r < this.numberOfRows && c >= 0 && c < this.numberOfColumns && grid[r][c] == NorthOfTheWall.WHITE_WALKER) {
                attacked++;
                newGrid[r][c] = NorthOfTheWall.EMPTY;
            }
        }
        if (attacked != 0)
            return new TheStateThatKnowsNothing(row, col, numberOfDragonGlasses - 1, theStateThatKnowsNothing.getCost() + 1, theStateThatKnowsNothing.getDepth() + 1, newGrid, state);
        return null;
    }

    private State pickUpDragonGlass(State state) {
        TheStateThatKnowsNothing theStateThatKnowsNothing = (TheStateThatKnowsNothing) state;
        int row = theStateThatKnowsNothing.getRow();
        int col = theStateThatKnowsNothing.getColumn();
        if (theStateThatKnowsNothing.getDragonGlasses() == this.numberOfDragonGlasses) return null;
        NorthOfTheWall[][] grid = theStateThatKnowsNothing.getGrid();
        if (grid[row][col] == NorthOfTheWall.DRAGON_STONE)
            // Action Cost is 0
            return new TheStateThatKnowsNothing(row, col, this.numberOfDragonGlasses, theStateThatKnowsNothing.getCost(), theStateThatKnowsNothing.getDepth() + 1, grid, state);
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
