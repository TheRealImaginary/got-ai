package com.ali.me.search;

import com.ali.me.problem.Problem;
import com.ali.me.state.State;

public abstract class SearchStrategy {

    public abstract State search(Problem problem);
}
