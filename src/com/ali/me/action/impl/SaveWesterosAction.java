package com.ali.me.action.impl;

import com.ali.me.action.Action;

public class SaveWesterosAction extends Action {

    public static final int MOVE_LEFT = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_UP = 2;
    public static final int MOVE_DOWN = 3;
    public static final int PICK_UP = 4;
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
