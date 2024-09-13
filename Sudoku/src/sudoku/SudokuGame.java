package sudoku;

import userInterface.gui;

public class SudokuGame {
	int[][] grid;;
	gui Screen;
	
	//Constructor
	public SudokuGame() {
		int[][] grid = new int[9][9]; //Empty grid
		
		this.Screen = new gui(grid); //The user interface with empty grid
		Screen.setVisible(true); //Set visible
	}
}
