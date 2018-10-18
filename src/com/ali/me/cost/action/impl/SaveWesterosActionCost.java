package com.ali.me.cost.action.impl;

import com.ali.me.action.Action;
import com.ali.me.action.impl.SaveWesterosAction;
import com.ali.me.cost.action.ActionCost;

/**
 * Cost Function for the SaveWeteros Problem.
 */
public class SaveWesterosActionCost implements ActionCost {

    @Override
    public int getActionCost(Action action) {
        // Should make attack depend on grid size => rows * cols * 1000
        // But won't matter as UCS, and A* would take time on large grids
        if (action.getAction() == SaveWesterosAction.ATTACK) return 5000;
        if (action.getAction() == SaveWesterosAction.PICK_UP) return 5;
        // Move
        return 10;
    }
}
