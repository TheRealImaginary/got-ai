package com.ali.me.state;

public abstract class State implements Comparable<State> {
    private int depth;
    private State parent;
    private int pathCost;
    private int heuristicCost;

    public State(int depth, State parent, int pathCost, int heuristicCost) {
        this.depth = depth;
        this.parent = parent;
        this.pathCost = pathCost;
        this.heuristicCost = heuristicCost;
    }

    public int getDepth() {
        return depth;
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

    public State getParent() {
        return parent;
    }

    @Override
    public String toString() {
        // We avoid printing the parent because printing would be long
        return "depth=" + depth +
                ", pathCost=" + pathCost +
                ", heuristicCost=" + heuristicCost;
    }
}
