package com.ali.me.problem;

import com.ali.me.cost.action.ActionCost;
import com.ali.me.cost.heuristic.Heuristic;
import com.ali.me.state.State;

import java.util.List;

/**
 * This class describes the structure of a Problem. Implementing Sub-classes
 * must implement and define the behaviour of each of the below abstract methods.
 */
public abstract class Problem {
    /**
     * The Initial State of this problem.
     */
    protected State initialState;

    /**
     * Expands the given state by applying all possible actions at this state and
     * returns a list of all the resulted states. The method should call {@link #expand(State, ActionCost, Heuristic)}
     * with a default ActionCost and Heuristic functions.
     * @param state The State to be expanded.
     * @return A list of all result states.
     */
    public abstract List<State> expand(State state);

    /**
     * Expands the given state by applying all possible actions at this state and
     * returns a list of all resulted states. The method should call {@link #expand(State, ActionCost, Heuristic)}
     * with a default ActionCost and the given Heuristic function.
     * @param state The State to be expanded
     * @param heuristic The Heuristic Function to apply to the resulted states.
     * @return A list of all result states.
     */
    public abstract List<State> expand(State state, Heuristic heuristic);

    /**
     * Expands the given state by applying all possible actions at this state and
     * returns a list of all resulted states.
     * @param state The State to be expanded
     * @param actionCost The ActionCost Function to add to the resulted states' Path Cost.
     * @param heuristic The Heuristic Function to apply to the resulted states.
     * @return A list f all result states.
     */
    public abstract List<State> expand(State state, ActionCost actionCost, Heuristic heuristic);

    /**
     * Tests whether a given state is a Goal state.
     * @param state The state to test
     * @return True if the given state is a Goal state, False otherwise.
     */
    public abstract boolean isGoal(State state);

    /**
     * A getter for this Problem's Initial state.
     * @return The Initial state of the problem.
     */
    public State getInitialState() {
        return this.initialState;
    }
}
