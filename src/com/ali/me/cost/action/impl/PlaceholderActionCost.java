package com.ali.me.cost.action.impl;

import com.ali.me.cost.action.ActionCost;
import com.ali.me.problem.impl.PlaceholderProblem;

public class PlaceholderActionCost implements ActionCost {
    @Override
    public int getActionCost(PlaceholderProblem.Action action) {
        if (action == PlaceholderProblem.Action.PICK_UP) return 0;
        return 1;
    }
}
