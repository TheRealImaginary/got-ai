package com.ali.me.cost.action.impl;

import com.ali.me.action.Action;
import com.ali.me.action.impl.PlaceholderAction;
import com.ali.me.cost.action.ActionCost;

public class PlaceholderActionCost implements ActionCost {
    @Override
    public int getActionCost(Action action) {
        if (action.getAction() == PlaceholderAction.ATTACK) return 1;
//        if (action == SaveWestrosProblem.Action.MOVE) return 1;
        return 0;
    }
}
