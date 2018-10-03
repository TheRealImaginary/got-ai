package com.ali.me.state.impl;

import com.ali.me.state.State;

import java.util.Arrays;

public class TheStateThatKnowsNothing extends State {

    public enum NorthOfTheWall {
        J,
        E,
        O,
        W,
        D
    }
    
    public enum Transition{
    	UP,
    	DOWN,
    	LEFT,
    	RIGHT,
    	PICKUP,
    	ATTACK
    }

    private int row;
    private int column;
    private int pathCost;
    private int heuristicCost;
    private int depth;
    private int dragonGlasses;
    private Transition trans;
    private State parent;
    private NorthOfTheWall[][] grid;

    public TheStateThatKnowsNothing(int row, int column, int dragonGlasses, int pathCost, int heuristicCost, int depth, NorthOfTheWall[][] grid, Transition trans ,State parent) {
        this.row = row;
        this.column = column;
        this.dragonGlasses = dragonGlasses;
        this.pathCost = pathCost;
        this.heuristicCost = heuristicCost;
        this.depth = depth;
        this.grid = grid;
        this.parent = parent;
        this.trans = trans;
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
                ", dragonGlasses=" + dragonGlasses +
                ", pathCost=" + pathCost +
                ", heuristicCost=" + heuristicCost +
                ", grid=" + Arrays.toString(grid) +
                '}';
    }

	public Transition getTrans() {
		return trans;
	}


}
