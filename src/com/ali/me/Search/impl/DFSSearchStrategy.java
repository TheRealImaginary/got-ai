package com.ali.me.Search.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import com.ali.me.Problem.Problem;
import com.ali.me.Search.SearchStrategy;
import com.ali.me.State.State;
import com.ali.me.State.impl.TheStateThatKnowsNothing;
import com.ali.me.State.impl.TheStateThatKnowsNothing.NorthOfTheWall;

public class DFSSearchStrategy extends SearchStrategy {

    @Override
    public State search(Problem problem) {
        
//    	State state = problem.getInitialState();
//    
//    	dfsQueue.push(state);
    	
    	Stack<State> dfsQueue = new Stack<State>();
    	dfsQueue.push(problem.getInitialState());
    	List<State> nextStatesInit;
    	
    	TreeSet<State> tree = new TreeSet<State>();
    	
    	while(!(dfsQueue.isEmpty())){
    		State state = dfsQueue.pop();
    		if (problem.isGoal(state)) {
    			return state;
    		}
    		
    		TheStateThatKnowsNothing newState = (TheStateThatKnowsNothing)state;
    		
    		
    		NorthOfTheWall[][] grid = ((TheStateThatKnowsNothing)state).getGrid();
    		
    		System.out.println(newState);
    		for (int i = 0;i < grid.length;i++)
    		    System.out.println(Arrays.toString(grid[i]));
    		System.out.println();
//    		try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
    		
    		tree.add(state);
    		if (((TheStateThatKnowsNothing)state).getDepth() == 50) {
    			continue;
    		}
    		nextStatesInit = problem.expand(state);
    		for(int i = 0; i < nextStatesInit.size(); i++) {
    			if(!(tree.contains(nextStatesInit.get(i)))){
        		dfsQueue.push(nextStatesInit.get(i));
    			}
        	}    		
    	}
    	return null;
    }
}
