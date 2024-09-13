package sudoku;

public class SudokuHelper {
	
	//Making a copy of a grid
	public static int[][] deepCopy(int[][] grid) { 
		int[][] copyGrid = new int[9][9]; //Setting new grid with 9 by 9
		
		for (int i = 0; i<grid.length ; i++) { //Loop between rows
			for (int j = 0; j<grid[0].length; j++) { //Loop between columns
				copyGrid[i][j] = grid[i][j]; //Copy the cells
			}
		}
		return copyGrid;
	}
	
	//Check if the given number is valid for given row
	public static boolean isValidRow(int[][] grid, int row, int num) {
		for (int i=0; i<grid[row].length; i++) { //Loop in the given row
			if (grid[row][i] == num) {
				return false;
			}
		}
		return true;
	}
	
	//Check if the given number is valid for given row
	public static boolean isValidCol(int[][] grid, int col, int num) {
		for (int i=0; i < grid.length; i++) { //Loop in the given column
			if (grid[i][col] == num) {
				return false;
			}
		}
		return true;
	}
	
	//Check if the given number is valid for the box of given coordinates
	public static boolean isValidBox(int[][] grid, int startRow, int startCol, int num) {
		int[] box;
		int row;
		int col;
		
		//Set the startingRow and startingColumn of the Box
		if (5<startRow) {
			if (5<startCol) {
				row = 8;
				col = 8;
			}
			else if (2<startCol) {
				row = 8;
				col = 5;
			}
			else {	
				row = 8;
				col = 2;
			}
		}
		else if (2<startRow) {
			if (5 < startCol) {
				row = 5;
				col = 8;
			}
			else if (2 < startCol) {
				row = 5;
				col = 5;
			}
			else {
				row = 5;
				col = 2;
			}
		}
		else {
			if (5 < startCol) {
				row = 2;
				col = 8;
			}
			else if (2 < startCol) {
				row = 2;
				col = 5;
			}
			else {
				row = 2;
				col = 2;
			}
		}
		
		//Set the box
		box = new int[]{grid[row-2][col-2],grid[row-2][col-1],grid[row-2][col],grid[row-1][col-2],grid[row-1][col-1],grid[row-1][col],grid[row][col-2],grid[row][col-1],grid[row][col]};

		for (int i : box) { //Loop in the box
			if (i == num) {
				return false;
			}
		}
		return true;
	}
}
	
	

