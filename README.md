# Sudoku Game Project

Overview
This project is a Sudoku Game written in Java. It allows users to:
- Play Sudoku: Interact with a Sudoku board by filling in numbers.
- Solve Sudoku: Automatically solve any valid Sudoku puzzle.
- Generate Sudoku Games: Create new solvable Sudoku puzzles of varying difficulties.
The game features a simple GUI for interaction, along with real-time visual feedback during the solving process.

Features
+ Sudoku Board: A 9x9 grid where users can input numbers.
+ Puzzle Submit: Submit your solved grid and check if it is correct or not.
+ Puzzle Solver: Automatically solves a Sudoku puzzle using a backtracking algorithm.
+ Puzzle Generator: Randomly generates new Sudoku puzzles with unique solutions.
+ Real-time Visualization: While solving, the algorithm visualizes each step, color-coding the numbers for easier understanding (green for correct placements, red for errors).
+ Difficulty Levels: Easy, Medium, and Hard modes for puzzle generation.

How to Play
1. Open the application.
2. To Play: Start a new game from New Game button and choose your difficulty level.
3. To Solve: Input your numbers to the sudoku cells according to sudoku rules.
4. When you think you are done press Submit and see the result.
5. In any situation press Solve button and choose solving speed for activating auto solving algorithm
6. While the algorithm solves, you can press Solve button again to cancel solving algorithm
Visual feedback will guide you while playing or solving puzzles.

How It Works
- The project uses a backtracking algorithm to solve puzzles.
- The puzzle generator ensures that generated puzzles have only one solution, maintaining the classic Sudoku rules.
- Visuals update dynamically to show the solving process in real-time.

Installation
1. Clone the Repository
2. Open Eclipse.
3. Go to File > Import.
4. Select Git > Projects from Git and click Next.
5. Choose Clone URI and click Next.
6. Paste the repository URL into the URI field and the rest of the fields should auto-fill.
7. Click Next, select the branches you want (usually just the main or master branch), and click Next again.
8. Choose the local directory where you'd like to store the project, and click Finish.
9. Go to File > Import.
10. Select Existing Projects into Workspace under General and click Next.
11. Browse to the directory where you cloned the repository and click Finish. This will import the project into Eclipse.
12. Once the project is imported, find the Sudoku file.
13. Right-click the file in the Project Explorer.
14. Choose Run As > Java Application.


