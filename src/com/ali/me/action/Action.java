package com.ali.me.action;

/**
 * Represents an Action. Actions should
 * be Integers as shown in {@link com.ali.me.action.impl.SaveWesterosAction}
 */
public abstract class Action {

    /**
     * The ID of the action taken.
     */
    private int action;

    public Action(int action) {
        this.action = action;
    }

    /**
     * Getter for the {@link Action#action}
     * @return The Action ID.
     */
    public int getAction() {
        return action;
    }

    /**
     * Returns a string representation of the action.
     * @return The String representing the action
     */
    public abstract String getActionString();
}
