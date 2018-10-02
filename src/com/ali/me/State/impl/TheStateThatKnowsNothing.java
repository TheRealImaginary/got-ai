package com.ali.me.State.impl;

import com.ali.me.State.State;

import java.util.Arrays;

public class TheStateThatKnowsNothing extends State{

    public enum NorthOfTheWall {
        JON,
        EMPTY,
        OBSTACLE,
        WHITE_WALKER,
        DRAGON_STONE
    }

    private int row;
    private int column;
    private int cost;
    private int depth;
    private int dragonGlasses;
    private State parent;
    private NorthOfTheWall[][] grid;

    public TheStateThatKnowsNothing(int row, int column, int dragonGlasses, int cost, int depth , NorthOfTheWall[][] grid, State parent) {
        this.row = row;
        this.column = column;
        this.dragonGlasses = dragonGlasses;
        this.cost = cost;
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

    public int getCost() {
        return cost;
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
                ", dragonGlasses=" + dragonGlasses +
                ", cost=" + cost +
                ", grid=" + Arrays.toString(grid) +
                '}';
    }
}
