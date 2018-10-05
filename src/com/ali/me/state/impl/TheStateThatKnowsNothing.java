package com.ali.me.state.impl;

import com.ali.me.state.State;

import java.util.Arrays;

public class TheStateThatKnowsNothing extends State {

    public enum NorthOfTheWall {
        J(0),
        E(1),
        O(2),
        W(3),
        D(4);
        public int id;

        NorthOfTheWall(int id) {
            this.id = id;
        }
    }

    private int row;
    private int column;
    private int pathCost;
    private int heuristicCost;
    private int depth;
    private int dragonGlasses;
    private State parent;
    private NorthOfTheWall[][] grid;

    public TheStateThatKnowsNothing(int row, int column, int dragonGlasses, int pathCost, int heuristicCost, int depth, NorthOfTheWall[][] grid, State parent) {
        this.row = row;
        this.column = column;
        this.dragonGlasses = dragonGlasses;
        this.pathCost = pathCost;
        this.heuristicCost = heuristicCost;
        this.depth = depth;
        this.grid = grid;
        this.parent = parent;
    }

    public int getRow() {
        return row;
    }


    public int getColumn() {
        return column;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public int getDepth() {
        return depth;
    }

    public int getDragonGlasses() {
        return dragonGlasses;
    }

    public State getParent() {
        return parent;
    }

    public NorthOfTheWall[][] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        return "TheStateThatKnowsNothing{" +
                "row=" + row +
                ", column=" + column +
                ", pathCost=" + pathCost +
                ", heuristicCost=" + heuristicCost +
                ", depth=" + depth +
                ", dragonGlasses=" + dragonGlasses +
                ", grid=" + Arrays.toString(grid) +
                '}';
    }

    @Override
    public int compareTo(State state) {
        TheStateThatKnowsNothing otherState = (TheStateThatKnowsNothing) state;
        if (this.row != otherState.row) return this.row - otherState.row;
        if (this.column != otherState.column) return this.column - otherState.column;
        if (this.dragonGlasses != otherState.dragonGlasses) return this.dragonGlasses - otherState.dragonGlasses;
        NorthOfTheWall[][] otherGrid = otherState.grid;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] != otherGrid[i][j]) return grid[i][j].id - otherGrid[i][j].id;
        return 0;
    }
}
