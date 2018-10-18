package com.ali.me.state;

import com.ali.me.action.Action;

/**
 * Represent a Node in the Search Tree.
 */
public abstract class State implements Comparable<State> {

    /**
     * Depth of this node in the Search Tree.
     */
    private int depth;

    /**
     * Parent of this Node.
     */
    private State parent;

    /**
     * Path Cost from root (Initial) node to this one.
     */
    private int pathCost;

    /**
     * Action taken at the previous node, hence resulting in this one.
     */
    private Action action;

    /**
     * Heuristic Cost of this node.
     */
    private int heuristicCost;

    /**
     * Populates the above properties.
     * @param depth
     * @param parent
     * @param pathCost
     * @param action
     * @param heuristicCost
     */
    public State(int depth, State parent, int pathCost, Action action, int heuristicCost) {
        this.depth = depth;
        this.parent = parent;
        this.pathCost = pathCost;
        this.action = action;
        this.heuristicCost = heuristicCost;
    }

    /**
     * Getter for {@link State#depth}
     * @return Returns the Depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Getter for {@link State#pathCost}
     * @return Returns the Path Cost
     */
    public int getPathCost() {
        return pathCost;
    }

    /**
     * Getter for {@link State#heuristicCost}
     * @return Returns the Heuristic Cost
     */
    public int getHeuristicCost() {
        return heuristicCost;
    }

    /**
     * Setter for {@link State#heuristicCost}
     * @param heuristicCost
     */
    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    /**
     * Getter for {@link State#parent}
     * @return Returns the Parent
     */
    public State getParent() {
        return parent;
    }

    /**
     * Getter for {@link State#action}
     * @return Returns the Action
     */
    public Action getAction() {
        return action;
    }

    /**
     * Returns a string representation of this node.
     * @return
     */
    @Override
    public String toString() {
        // We avoid printing the parent because printing would be long
        return "depth=" + depth +
                ", pathCost=" + pathCost +
                ", action=" + action +
                ", heuristicCost=" + heuristicCost;
    }
}
