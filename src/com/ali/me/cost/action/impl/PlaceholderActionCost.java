package com.ali.me.cost.action.impl;

import com.ali.me.cost.action.ActionCost;
import com.ali.me.problem.impl.PlaceholderProblem;

public class PlaceholderActionCost implements ActionCost {
    @Override
    public int getActionCost(PlaceholderProblem.Action action) {
        if (action == PlaceholderProblem.Action.ATTACK) return 1;
//        if (action == PlaceholderProblem.Action.MOVE) return 1;
        return 0;
    }
}
