package com.ali.me.State.impl;

import com.ali.me.State.State;

import java.util.Arrays;

public class TheStateThatKnowsNothing extends State implements Comparable<TheStateThatKnowsNothing> {

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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TheStateThatKnowsNothing other = (TheStateThatKnowsNothing) obj;
		if (column != other.column)
			return false;
		if (cost != other.cost)
			return false;
		if (depth != other.depth)
			return false;
		if (dragonGlasses != other.dragonGlasses)
			return false;
		if (!Arrays.deepEquals(grid, other.grid))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public int compareTo(TheStateThatKnowsNothing s) {
		if(this.equals(s))
		return 0;
		
		return -1;
	}

	


}
