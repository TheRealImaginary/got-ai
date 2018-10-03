//package com.ali.me.cost.heuristic.impl;
//
//import com.ali.me.cost.heuristic.Heuristic;
//import com.ali.me.state.State;
//import com.ali.me.state.impl.TheStateThatKnowsNothing;
//import com.ali.me.state.impl.TheStateThatKnowsNothing.NorthOfTheWall;
//
//public class NoMovementRestrictionHeuristic implements Heuristic {
//
//	public int heuristicCost(State state) {
//		State heuristicState = state;
//		TheStateThatKnowsNothing.NorthOfTheWall[][] grid = ((TheStateThatKnowsNothing) heuristicState).getGrid();
//		int numberOfWhiteWalkers = 0;
//		for (int i = 0; i < grid.length; i++)
//			for (int j = 0; j < grid[i].length; j++) {
//				if (i == 0 && j == 0){
//					if()
//				}
//				
//				if (i == 0 && j == grid.length - 1) {
//					
//				}
//				
//				if (i == grid.length - 1 && j == 0) {
//					
//				}
//				
//				if (i == grid.length - 1 && j == 1) {
//					
//				}
//				
//				
//				//This is part of the code that we might want to revisit in a different heuristic function
////					if (i < 2) {
////						grid[i+2][j] = NorthOfTheWall.EMPTY;
////					}else
////					if(i > grid.length-3) {
////						grid[i-2][j] = NorthOfTheWall.EMPTY;
////					}else {
////					grid[i+2][j] = NorthOfTheWall.EMPTY;
////					grid[i-2][j] = NorthOfTheWall.EMPTY;
////					}
////					if (j < 2) {
////						grid[i][j+2] = NorthOfTheWall.EMPTY;
////					}else
////					if(j > grid[i].length-3) {
////						grid[i][j-2] = NorthOfTheWall.EMPTY;
////					}else {
////						grid[i][j+2] = NorthOfTheWall.EMPTY;
////						grid[i][j-2] = NorthOfTheWall.EMPTY;
////					}
////					
////					if (i == 0 && j == 0) {
////						grid[i+1][j+1] = NorthOfTheWall.EMPTY;
////					}
////					if (i == 0 && j == grid.length - 1) {
////						grid[i+1][j+1] = NorthOfTheWall.EMPTY;
////					}
////					if (i == grid.length - 1 && j == 0) {
////						grid[i-1][j-1] = NorthOfTheWall.EMPTY;
////					}
////					if (i == grid.length - 1 && j == 1) {
////						grid[i+1][j+1] = NorthOfTheWall.EMPTY;
////					}
////					
////					if(i == 0 & j != 0 & j != grid.length) {
////						grid[i+1][j+1] = NorthOfTheWall.EMPTY;
////						grid[i-1][j+1] = NorthOfTheWall.EMPTY;
////					}
////					if(i == grid.length-1 & j != 0 & j != grid.length) {
////						grid[i+1][j-1] = NorthOfTheWall.EMPTY;
////						grid[i-1][j-1] = NorthOfTheWall.EMPTY;
////					}
//					
//				
//			}
//	}
//}
