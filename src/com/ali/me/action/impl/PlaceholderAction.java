package com.ali.me.action.impl;

import com.ali.me.action.Action;

public class PlaceholderAction extends Action {

    public static final int MOVE_LEFT = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_UP = 2;
    public static final int MOVE_DOWN = 3;
    public static final int PICK_UP = 4;
    public static final int ATTACK = 5;

    public PlaceholderAction(int action) {
        super(action);
    }

    @Override
    public String toString() {
        String action;
        switch (this.getAction()) {
            case MOVE_LEFT:
                action = "MOVE_LEFT";
                break;
            case MOVE_RIGHT:
                action = "MOVE_RIGHT";
                break;
            case MOVE_UP:
                action = "MOVE_UP";
                break;
            case MOVE_DOWN:
                action = "MOVE_DOWN";
                break;
            case PICK_UP:
                action = "PICK_UP";
                break;
            case ATTACK:
                action = "ATTACK";
                break;
            default:
                action = "NO_ACTION";
                break;
        }
        return String.format("PlaceholderAction{%s}", action);
    }
}
