package com.ali.me.cost.action;


import com.ali.me.action.Action;

/**
 * Action Cost Function
 */
public interface ActionCost {

    /**
     * Returns the cost of taking a
     * given Action.
     * @param action
     * @return
     */
    int getActionCost(Action action);
}
