package com.ali.me.action.impl;

import com.ali.me.action.Action;

/**
 * Represents all Actions that can be taken
 * in the SaveWesteros Problem.
 */
public class SaveWesterosAction extends Action {

    /**
     * Move Left Action.
     */
    public static final int MOVE_LEFT = 0;

    /**
     * Move Right Action.
     */
    public static final int MOVE_RIGHT = 1;

    /**
     * Move Up Action.
     */
    public static final int MOVE_UP = 2;

    /**
     * Move Down Action.
     */
    public static final int MOVE_DOWN = 3;

    /**
     * Pick Up Action.
     */
    public static final int PICK_UP = 4;

    /**
     * Attack Action.
     */
    public static final int ATTACK = 5;

    public SaveWesterosAction(int action) {
        super(action);
    }

    public String getActionString() {
        String action;
        switch (this.getAction()) {
            case MOVE_LEFT:
                return "MOVE_LEFT";
            case MOVE_RIGHT:
                return "MOVE_RIGHT";
            case MOVE_UP:
                return "MOVE_UP";
            case MOVE_DOWN:
                return "MOVE_DOWN";
            case PICK_UP:
                return "PICK_UP";
            case ATTACK:
                return "ATTACK";
            default: // Should not happen
                return "NO_ACTION";
        }
    }

    @Override
    public String toString() {
        return String.format("SaveWesterosAction{%s}", getActionString());
    }
}
