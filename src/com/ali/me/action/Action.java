package com.ali.me.action;

public abstract class Action {

    private int action;

    public Action(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
