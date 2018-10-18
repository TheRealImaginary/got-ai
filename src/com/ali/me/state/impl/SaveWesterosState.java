package com.ali.me.state.impl;

import com.ali.me.action.Action;
import com.ali.me.state.State;

import java.util.Arrays;

/**
 * Represents a State for the SaveWesteros Problem.
 */
public class SaveWesterosState extends State {

    /**
     * Enum for Cell Type
     */
    public enum NorthOfTheWall {
        /**
         * Jon
         */
        J(0),
        /**
         * Empty
         */
        E(1),
        /**
         * Obstacle
         */
        O(2),
        /**
         * White Walker
         */
        W(3),
        /**
         * Dragon Stone
         */
        D(4);
        /**
         * ID Used in comparing the grid
         */
        public int id;

        /**
         * @param id ID of this cell type
         */
        NorthOfTheWall(int id) {
            this.id = id;
        }
    }

    /**
     * Row that Jon is currently in.
     */
    private int row;

    /**
     * Column that Jon is currently in.
     */
    private int column;

    /**
     * Number of Dragon Glasses that Jon is
     * currently carrying.
     */
    private int dragonGlasses;

    /**
     * The Grid.
     */
    private NorthOfTheWall[][] grid;

    /**
     * Creates an Instance of the SaveWesteros State.
     * @param depth Depth of this state.
     * @param parent Parent of this state.
     * @param pathCost Path Cost from root (Initial state) to this one.
     * @param action Previous Action taken.
     * @param heuristicCost Heuristic cost.
     * @param row Row that Jon is currently in.
     * @param column Column that Jon is currently in.
     * @param dragonGlasses Number of Dragon Glasses that Jon is currently carrying.
     * @param grid The Grid.
     */
    public SaveWesterosState(int depth, State parent, int pathCost, Action action, int heuristicCost, int row, int column, int dragonGlasses, NorthOfTheWall[][] grid) {
        super(depth, parent, pathCost, action, heuristicCost);
        this.row = row;
        this.column = column;
        this.dragonGlasses = dragonGlasses;
        this.grid = grid;
    }

    /**
     * Getter for {@link SaveWesterosState#row}
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for {@link SaveWesterosState#column}
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     * Getter for {@link SaveWesterosState#dragonGlasses}
     * @return
     */
    public int getDragonGlasses() {
        return dragonGlasses;
    }

    /**
     * Getter for {@link SaveWesterosState#grid}
     * @return
     */
    public NorthOfTheWall[][] getGrid() {
        return grid;
    }

    /**
     * Returns a string representation of this state.
     * @return
     */
    @Override
    public String toString() {
        return "SaveWesterosState{" +
                "row=" + row +
                ", column=" + column +
                ", dragonGlasses=" + dragonGlasses +
                ", " + super.toString() +
                ", grid=" + Arrays.deepToString(grid) +
                '}';
    }

    /**
     * Compares two states. This is used in Search Strategy
     * to check for previously visited States and their costs.
     * @param state
     * @return
     */
    @Override
    public int compareTo(State state) {
        SaveWesterosState otherState = (SaveWesterosState) state;
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
