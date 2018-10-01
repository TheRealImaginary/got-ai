package com.ali.me.Search.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.ali.me.Problem.Problem;
import com.ali.me.Search.SearchStrategy;
import com.ali.me.State.State;

public class DFSSearchStrategy extends SearchStrategy {

    @Override
    public State search(Problem problem) {
        
//    	State state = problem.getInitialState();
//    
//    	dfsQueue.push(state);
    	
    	Stack<State> dfsQueue = new Stack<State>();
    	List<State> nextStatesInit = problem.expand(problem.getInitialState());
    	
    	while(!(dfsQueue.isEmpty())){
    		if (problem.isGoal(dfsQueue.peek())) {
    			return dfsQueue.pop();
    		}
    		nextStatesInit = problem.expand(dfsQueue.pop());
    		for(int i = 0; i < nextStatesInit.size(); i++) {
        		dfsQueue.push(nextStatesInit.get(i));
        	}    		
    	}
    	return null;
    }
}
