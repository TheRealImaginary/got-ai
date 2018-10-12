package com.ali.me.search;

import com.ali.me.problem.Problem;
import com.ali.me.state.State;

/**
 * This class describes the structure of a Search Strategy. Implementing Sub-classes
 * must implement and define the behaviour of the following abstract method.
 */
public abstract class SearchStrategy {

    public abstract State search(Problem problem);
}
