/* 
Esther Wu 
3/24/2021

I first created the method createBoard that fills up an array with blanks. This is so
that when I create the game board around an array that holds information of the ship 
location. I then created the method printBoard that creates the grid, and with shipPlace,
this randomly generates the location of a ship within the bounds. After this, the user 
is asked for coordinates. This continues until the user guesses all the coordinates that
the ship is located. 
*/

import java.util.Scanner;

public class Battleship{
	public static void main(String [] args){
		// initialize board, columns are identified by a letter and rows by a number
		final int shipSize = 4;
		final int dimension = 10;
		
		int guess = 0; // number of guesses made
		int answer = 0; // counting how many boxes of the ship the user guessed

		// board with the actual ship
		char [][] board = new char [dimension][dimension];
		// user's input board
		char [][] answerBoard = new char[dimension][dimension];

		// creating both answer and input board
		createBoard(board);
		createBoard(answerBoard);
		// showing the user's inputs
		printBoard(answerBoard);
		// placing a ship at a random location
		shipPlace(board, shipSize);

		// for grading purposes: if you need to see the computer's placement of the ship, uncomment this:
		//printBoard(board);

		// create scanner object
		Scanner in = new Scanner(System.in);

		//Extra credit: personalizing game by asking user's name
		System.out.print("Hi! What is your name? ");
		String name = in.next();

		while (answer != shipSize){
			// asks user for coordinates
			System.out.print(name + ", enter coordinate to target (e.g A1): ");
			String location = in.next();
			boolean valid = false;

			// checking coordinates
			while (valid == false){ // keep asking until the coordinates fit the style	
				// checking if input is being repeatedly entered
				if (answerBoard[(int) location.charAt(1) - 48][(int) location.charAt(0) - 65] == 'X' || answerBoard[(int) location.charAt(1) - 48][(int) location.charAt(0) - 65] == '#'){
					System.out.print("Input invalid, choose another coordinate: ");
					location = in.next();
				}
				// if first value is a letter and the second value is a number
				else if ((int) location.charAt(0) < 65 + dimension && (int) location.charAt(0) > 64 && (int) location.charAt(1) > 47 && (int) location.charAt(1) < 48 + dimension && location.length() == 2){
					valid = true;
				}
				else{
					System.out.print("Input invalid, choose another coordinate: ");
					location = in.next();
				}

			}
			// checking if the location has the ship in it
			if (board[(int) location.charAt(1) - 48][(int) location.charAt(0) - 65] == 'X'){
				answer++;
				guess++;

				answerBoard[(int) location.charAt(1) - 48][(int) location.charAt(0) - 65] = 'X';
			}
			// if the ship isn't there
			else{
				guess++;
				answerBoard[(int) location.charAt(1) - 48][(int) location.charAt(0) - 65] = '#';
			}

			// prints the user's inputs on a board
			printBoard(answerBoard);
		}
		System.out.println("Congrats! You win, using " + guess + " guesses!");

	}

	// creating board
	public static void createBoard(char [][] board){
		// initializing first row of board
		for (int col = 0; col < board[0].length; col++){
			//board[0][col] = (char) (col + 65);
			// initializing first column of board
			for (int row = 0; row < board[1].length; row++){
				board[row][col] = ' ';
			}
		}	
	} 

	public static void printBoard(char [][] board){

		// print 3 spaces at start before header coulmn
		System.out.print("   ");
		// print column header of numbers (representing rows)    A  B  C...

		for(int border = 0; border < board[0].length; border++){
			
			System.out.print((char) (border + 65));
			System.out.print("   ");
		}
		// print 3 spaces at end
		System.out.print("   ");
		System.out.println();
		System.out.print(" +");

		// print top border (horizental line of +---+---+...)
		for(int border = 0; border < board[0].length; border++){
			System.out.print("---+");
		}

		System.out.println();

		// print the starting number of the first column
		for(int r = 0; r < board.length; r++){
			
			System.out.print( r + "|");

			// print the values of the array with the barriers between
			for(int c = 0; c < board[r].length; c++){
				System.out.print(" ");
				System.out.print(board[r][c]);
				System.out.print(" |");
			}
			System.out.println();
		System.out.print(" +");

		// print the borders between columns
		for (int mid = 0; mid < board.length; mid++){

			System.out.print("---+");
		}
		System.out.println();
		}
	}

	public static void shipPlace(char [][] board, int size){
		// randomly choosing orientation of ship (0 = vertically down, 1 = horizontally right)
		int orientation = (int) (Math.random() + 0.5);
		boolean status = false;

		// checking if ship fits in the board
		while (status == false){
			// depending on orientation, we add to col (0) or row (1)
			if (orientation == 0){

				// find a number between 0 to dimensions for coordinates
				int row = (int) (Math.random() * (board[1].length - size));
				int col = (int) (Math.random() * board[0].length);

				// checking if it fits the board
				if (row < board[0].length && (col + size) < board[1].length){
					//planting boat on board
					for (int i = 0; i < size; i++){
						board[col][row + i] = 'X';}

					status = true;
				}				
			}
			else if (orientation == 1){

				// find a number between 0 to dimensions for coordinates
				int row = (int) (Math.random() * board[1].length);
				int col = (int) (Math.random() * (board[0].length - size));

				// checking if it fits the board
				if (col < board[1].length && (row + size) < board[0].length){
					for (int i = 0; i < size; i++){
						board[col + i][row] = 'X';}

					status = true;
				}		
			}
		}
	}
}
