package sudoku;

import java.util.Random;

public class SudokuCreator {
	private int[][] grid;
	private SudokuSolver solver;
	
	
	public SudokuCreator() {
		SudokuSolver solver = new SudokuSolver(null); //Creating a general solver for SudokuCreator class
		this.solver = solver;
		resetTheGrid();
	}
	
	//Creating a valid sudoku with empty cells
	public int[][] createSudoku(int difficulty) {
		getRandomSolvedGrid(); //make this.grid to random solved grid
		
		for (int i=0; i < difficulty; i++) { //Loop this method according to difficulty (The difficulty will increase as you repeat the method more)
			deleteCellWhileMaintainingValidity(); //Deleting a cell with keeping the validity of sudoku
		}
		int[][] newGrid = grid; //Store the created Sudoku grid
		resetTheGrid(); //Reset the previous one for next sudoku grid
		
		return newGrid; //Return the stored grid
	}
	
	//Creating a random fully solved grid
	public void getRandomSolvedGrid() {
		for (int loopNumber = 0; loopNumber < 3; loopNumber++) { //Loop this 3 times, this will make sure everything is random
			for (int i=0; i < 20; i++) { //Delete 20 random cells and replace them with solving
				deleteRandomCell();
			}
			this.grid = solver.solveSudoku(grid); //Replace randomly deleted cells
		}
	}
	
	//Delete a random cell from the grid
	public void deleteRandomCell() {
		Random random = new Random();
        int randomRow = random.nextInt(9); //Random row
        int randomCol = random.nextInt(9); //Random column
 
        this.grid[randomCol][randomRow] = 0; //Make random cell empty by putting 0 in it
	}
	
	//Setting grid to the basic grid
	public void resetTheGrid() {
		int[][] grid = { //Solved Sudoku Grid
	            {5,3,4,6,7,8,9,1,2},
	            {6,7,2,1,9,5,3,4,8},
	            {1,9,8,3,4,2,5,6,7},
	            {8,5,9,7,6,1,4,2,3},
	            {4,2,6,8,5,3,7,9,1},
	            {7,1,3,9,2,4,8,5,6},
	            {9,6,1,5,3,7,2,8,4},
	            {2,8,7,4,1,9,6,3,5},
	            {3,4,5,2,8,6,1,7,9}
	        };
		
		this.grid = grid; //Setting grid to the basic grid
	}
	
	//Deleting a cell with keeping the validity of sudoku, a sudoku grid with single solution is called valid sudoku
	public void deleteCellWhileMaintainingValidity() {
		Random random = new Random();
        int randomRow = random.nextInt(9); //Random row
        int randomCol = random.nextInt(9); //Random column
        
        int deletedCell = grid[randomCol][randomRow]; //Store the random deleted cell's number
        this.grid[randomCol][randomRow] = 0; //Make chosen cell empty
        
        if (solver.isSingleSolution(grid)) { //Check if the grid still valid even with the deleted cell
        	return; //If it is still valid keep everything and end the method
        }
        this.grid[randomCol][randomRow] = deletedCell; //If it is not valid then reput the deleted number to chosen cell
	}
	
	public void printGrid(int[][] grid) { //Print to console
		for(int[] row : grid) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println("");
		}
	}
}
