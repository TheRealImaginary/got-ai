package com.ali.me.cost.action.impl;

import com.ali.me.action.Action;
import com.ali.me.action.impl.SaveWesterosAction;
import com.ali.me.cost.action.ActionCost;

public class SaveWesterosActionCost implements ActionCost {
    @Override
    public int getActionCost(Action action) {
        if (action.getAction() == SaveWesterosAction.ATTACK) return 1;
//        if (action == SaveWesterosProblem.Action.MOVE) return 1;
        return 0;
    }
}
