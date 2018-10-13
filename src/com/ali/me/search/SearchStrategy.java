package com.ali.me.search;

import com.ali.me.problem.Problem;
import com.ali.me.state.State;

public abstract class SearchStrategy {

    protected static final long POSITIVE_INFINITY = 1L << 58;

    public abstract void expand(Problem problem, State state);

    protected abstract boolean add(State state);

    protected abstract State pop();

    protected abstract boolean isEmpty();
}
