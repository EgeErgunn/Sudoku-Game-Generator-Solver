package sudoku;

import java.awt.Color;

import javax.swing.SwingWorker;

import userInterface.gui;

public class SudokuSolver {
	
	private int[][] grid;
	private int[][] solution;
	private int SIZE = 9;
	private gui screen;
	private SwingWorker<Void, Void> worker;
	
	//Constructor
	public SudokuSolver(gui screen) {
		this.screen = screen;
	}
	
	//Normal sudoku solve Method with backtracking
	public int[][] solveSudoku(int[][] grid) {
        this.grid = grid;
        int[][] solution = SudokuHelper.deepCopy(grid);
        this.solution = solution;
        int[][] EmptyCells = findEmptyCells(); //Empty cells coordinates 
        int CurrentCellIndex = 0; //Current empty cells index, it only travels between empty cells
        int TRY = grid[EmptyCells[0][0]][EmptyCells[0][1]] + 1; //TRY is the number we try for the current empty cell
        while (CurrentCellIndex > -1) { //The algorithm continues as long as there are empty cells that we can try
        	while (TRY < 10) { //The algorithm continues as long as the number we are TRY'ing is lower than 10
        		if (isValidMove(EmptyCells[CurrentCellIndex][0], EmptyCells[CurrentCellIndex][1], TRY)) { //Check if we can write TRY to current empty cell
        			solution[EmptyCells[CurrentCellIndex][0]][EmptyCells[CurrentCellIndex][1]] = TRY; //Make the current empty cell the number we TRY'ied
                    CurrentCellIndex += 2; //If its valid than add 2 to cell Index (algorithm always decrease 1 at the end of everything so basically it will increase only 1
                    TRY =1; //Found a valid number for emtpy cell and stepping to next empty cell with TRY'ing 1
                    break; //Skip the TRY++ part
                    }
        		TRY++; //If it not valid then increment TRY by one and try again
        		}
        	if (TRY == 10) { //Which means we can not put any number to current empty cell
        		solution[EmptyCells[CurrentCellIndex][0]][EmptyCells[CurrentCellIndex][1]] = 0; //Make the current empty cell, empty
        		if (CurrentCellIndex == 0) {
        			break; //If we are bottom of the current cells break the the algorithm
        		}
        		TRY = solution[EmptyCells[CurrentCellIndex - 1][0]][EmptyCells[CurrentCellIndex - 1][1]] + 1; //Backtracking
        		}
        	CurrentCellIndex--; //Backtracking
        	if (EmptyCells[CurrentCellIndex][0] == 90) { //If we hit the limit stop the algorithm
        		return solution;
        		}
        	}
        return null;

    }
	
	//Solve method using backtracking with GUI visualization, to visualize use the SwingWorker doInBackground
	public void solveSudoku(int[][] grid, gui screen, int speed) {
        this.grid = grid;
        int[][] solution = SudokuHelper.deepCopy(grid);
        this.solution = solution;
        int[][] EmptyCells = findEmptyCells(); //Empty cells coordinates 
        
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                int CurrentCellIndex = 0; //Current empty cells index, it only travels between empty cells
                int TRY = grid[EmptyCells[0][0]][EmptyCells[0][1]] + 1; //TRY is the number we try for the current empty cell
                
                while (CurrentCellIndex > -1) { //The algorithm continues as long as there are empty cells that we can try
                	if (isCancelled()) {  // Check if the worker was cancelledSystem.out.println("Task was canceled.");
                        break;  // Stop the solving process
                    }
                	try {
                        Thread.sleep(speed); //Delay for the visualization
                        if (isCancelled()) {  // Check if the worker was canceled during sleep
                            break;  // Exit the loop if task is canceled
                        }
                    } catch (InterruptedException e) {
                    	if (isCancelled()) {
                            return null;  // Exit the task if it's been canceled
                        }
                        e.printStackTrace();
                    }
                    while (TRY < 10) { //The algorithm continues as long as the number we are TRY'ing is lower than 10
                    	
                        screen.rewriteTheBox("  "+String.valueOf(TRY), EmptyCells[CurrentCellIndex][0], EmptyCells[CurrentCellIndex][1]); //Write the number we TRY'ied to the current empty cell

                        if (isValidMove(EmptyCells[CurrentCellIndex][0], EmptyCells[CurrentCellIndex][1], TRY)) { //Check if we can write TRY to current empty cell
                        	
                            screen.colorTheBox(Color.green, EmptyCells[CurrentCellIndex][0], EmptyCells[CurrentCellIndex][1]); //If we find a valid number for current empty cell, color the cell to green
                            
                            solution[EmptyCells[CurrentCellIndex][0]][EmptyCells[CurrentCellIndex][1]] = TRY; //Make the current empty cell the number we TRY'ied
                            CurrentCellIndex += 2; //If its valid than add 2 to cell Index (algorithm always decrease 1 at the end of everything so basically it will increase only 1
                            TRY =1; //Found a valid number for emtpy cell and stepping to next empty cell with TRY'ing 1
                            break; //Skip the TRY++ part
                        }
                        TRY++; //If it not valid then increment TRY by one and try again
                    }
                    
                    if (TRY == 10) { //Which means we can not put any number to current empty cell
                    	
                        screen.colorTheBox(Color.red, EmptyCells[CurrentCellIndex][0], EmptyCells[CurrentCellIndex][1]); //If we can not put any number to current empty cell, color the cell red
                        screen.rewriteTheBox("  X", EmptyCells[CurrentCellIndex][0], EmptyCells[CurrentCellIndex][1]); //If we can not put any number to current empty cell, write "X" to the cell
                        
                        solution[EmptyCells[CurrentCellIndex][0]][EmptyCells[CurrentCellIndex][1]] = 0; //Make the current empty cell, empty
                        
                        if (CurrentCellIndex == 0) {
                        	break; //If we are bottom of the current cells break the the algorithm
                        }
                        TRY = solution[EmptyCells[CurrentCellIndex - 1][0]][EmptyCells[CurrentCellIndex - 1][1]] + 1; //Backtracking
                    }
                    CurrentCellIndex--; //Backtracking
                    if (EmptyCells[CurrentCellIndex][0] == 90) { //If we hit the limit stop the algorithm
                    	break;
                    }
                }
				return null;
            }
            @Override
            protected void done() {
            	screen.enableEveryButton(); //Make every button enable
            	screen.resetTheSolve(); //When the solving done, reset the solve method
            }
        };
        this.worker = worker;
        worker.execute();
    }
	
	//Check if the given grid is correct
	public boolean isTheGridCorrect(int[][] grid) {
		boolean isTheGridCorrect = true; //Set the variable true for beginning
		this.grid = grid; //Every cell is filled at this grid
		int[][] solution = SudokuHelper.deepCopy(grid);
		this.solution = solution;
		for (int row = 0; row < 9; row++) { //Loop between rows
			for (int col = 0; col < 9; col++) { //Loop between columns
				int TRY = solution[row][col]; //Store the current cell number
				solution[row][col] = 0; //Set the current cell to 0 for checking if the number correct
				if (!isValidMove(row, col, TRY)) { //Check is the cell is correct with current number
					isTheGridCorrect = false; //Set the variable false, do not return yet because we want to determine all wrong numbers on the grid
					
					screen.colorTheBox(Color.red, row, col); //Color the false cell to red
				}
				solution[row][col] = TRY; //Set the grid to the default version
			}
		}
		return isTheGridCorrect;
	}
	
	public void cancelTask() {
            worker.cancel(true); // Cancel the worker
            screen.enableEveryButton();
    }
	
	//Check if the given grid has a single solution or not
	public boolean isSingleSolution(int[][] grid) { 
		this.grid = grid;
		int validSolutions = 0; //Number of valid solutions
		int CurrentCellIndex = 0; 
		int[][] solution = SudokuHelper.deepCopy(grid);
		this.solution = solution;
		int[][] EmptyCells = findEmptyCells();
		int TRY = grid[EmptyCells[0][0]][EmptyCells[0][1]]+1; //We add +1 to first empty cell in order to start the backtracking.
		while (CurrentCellIndex>-1) {
			while (10>TRY) {
				
				if (isValidMove(EmptyCells[CurrentCellIndex][0], EmptyCells[CurrentCellIndex][1], TRY)) { //Check if we can write TRY to current empty cell
                    
                    solution[EmptyCells[CurrentCellIndex][0]][EmptyCells[CurrentCellIndex][1]] = TRY; //Make the current empty cell the number we TRY'ied
                    CurrentCellIndex += 2; //If its valid than add 2 to cell Index (algorithm always decrease 1 at the end of everything so basically it will increase only 1
                    TRY =1; //Found a valid number for emtpy cell and stepping to next empty cell with TRY'ing 1
                    break; //Skip the TRY++ part
                }
                TRY++; //If it not valid then increment TRY by one and try again
            }
            
            if (TRY == 10) { //Which means we can not put any number to current empty cell
                
                solution[EmptyCells[CurrentCellIndex][0]][EmptyCells[CurrentCellIndex][1]] = 0; //Make the current empty cell, empty
                
                if (CurrentCellIndex == 0) {
                	break; //If we are bottom of the current cells break the the algorithm
                }
                TRY = solution[EmptyCells[CurrentCellIndex - 1][0]][EmptyCells[CurrentCellIndex - 1][1]] + 1; //Backtracking
            }
            CurrentCellIndex--; //Backtracking
            
            if (EmptyCells[CurrentCellIndex][0] == 90) { //If we hit the limit instead of stopping the algorithm continue backtracking
            	validSolutions++; //Everytime we hit the limit it means we found a valid solution
            	if (validSolutions == 2) { //Which means sudoku is not valid
            		return false;
            	}
            	TRY = solution[EmptyCells[CurrentCellIndex - 1][0]][EmptyCells[CurrentCellIndex - 1][1]] + 1; //Backtracking
            	CurrentCellIndex--; //Backtracking
            }
		}
		return true; //If a sudoku has single solution, it is a valid sudoku
	}

	private int[][] findEmptyCells() {
		int[][] emptyCells = new int[82][2]; //Empty cell coordinates
		int emptyCellNumber = 0;
		
		 for (int i = 0; i < emptyCells.length; i++) { //Set coordinates -1, -1
		        emptyCells[i][0]= -1; 
		        emptyCells[i][1]= -1;
		    }
		for (int r=0; r<SIZE; r++) { //Loop between rows
			for (int c=0; c<SIZE; c++) { //Loop between colums
				if (grid[r][c] == 0) { //If a cell in the grid has a 0 in it, it means empty
					emptyCells[emptyCellNumber][0] = r; //Finding the coordinates of the empty cells
					emptyCells[emptyCellNumber][1] = c; //r = row  c = column
					emptyCellNumber++;
				}
			}
		}
		emptyCells[emptyCellNumber][0] = 90; //To set the limit
		
		return emptyCells;
	}
	
	private boolean isValidMove(int row, int col, int num) { //Check if the given number is valid for the given row and column
		if ( SudokuHelper.isValidRow(solution,row,num)==true && SudokuHelper.isValidCol(solution,col,num)==true && SudokuHelper.isValidBox(solution,row,col,num)==true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void printGrid(int[][] grid) { //Print the grid to console
		for(int[] row : grid) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println("");
		}
	}
	
}
	
	
	
	


