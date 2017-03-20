package TicTacToe;

/* Author: Irish Marquez 
 * Class: Informatics 122 
 * Final Project (Client-Server Board Game Application)
 * Date: March 5, 2017
 * Description: TicTacToe Logic
 * */

import java.util.Scanner;

public class Driver{ 

	static int x = 3;	//grid
	static int y = 3; 	//grid
	static int[][] board = {{0,0,0},{0,0,0},{0,0,0}};

	static int flagTotal = 0; // used to keep track of how many turns there has been 
	
	static Scanner scan;
	
	static int player1 = 1;	//value that is assigned to the player's occupied grid
	static int player2 = 2;
	static boolean p1sTurn = false;
	static boolean p2sTurn = false;
	static boolean p1Wins = false;
	static boolean p2Wins = false;
	static String buttonLetter;
	
	static boolean hasWinner = false;
		
	public static void displayBoard() {
		int i, j;
		for (i = 0; i < x; ++i)
		{
			for (j = 0; j < y; ++j)
			{
				System.out.print(board[i][j]);
				if (j < y-1)
					System.out.print(" ");
				else
					System.out.println();
			}	
		}
		System.out.println();
	}
	
	
	public static void getInput(int row, int col) { //row is y value. col is x value
		//if (p1sTurn == true) 
		if (buttonLetter.equalsIgnoreCase("x"))
		{
			board[row][col] = 1;
		}

		else
		{
			board[row][col] = 2;
		}
		++flagTotal;
	}

	public static boolean checkForWin()  {	
		//CHECK ROWS
		if ( (board[0][0] != 0) && (board[0][0] == board[0][1]) && (board[0][1] == board[0][2])) //row1
		{
			hasWinner = true;
			if (board[0][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		else if ( (board[1][0] != 0) && (board[1][0] == board[1][1]) && (board[1][1] == board[1][2]) ) //row2
		{	
			hasWinner = true;
			if (board[1][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}			
		}
		else if ((board[2][0] != 0) && (board[2][0] == board[2][1]) && (board[2][1] == board[2][2]) ) //row3
		{
			hasWinner = true;
			if (board[2][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		// CHECK COLUMNS
		else if ( (board[0][0] != 0) && (board[0][0] == board[1][0]) && (board[1][0] == board[2][0]) ) //col1			
		{
			hasWinner = true;
			if (board[0][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		else if ( (board[0][1] != 0) && (board[0][1] == board[1][1]) && (board[1][1] == board[2][1]) ) //col2			
		{
			hasWinner = true;
			if (board[0][1] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		else if ( (board[0][2] != 0) && (board[0][2] == board[1][2]) && (board[1][2] == board[2][2]) ) //col3	
		{
			hasWinner = true;
			if (board[0][2] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		// CHECK DIAGONALS
		else if ( (board[0][0] != 0) &&(board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) ) // Diagonal (\)
		{
			hasWinner = true;
			if (board[0][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		else if ( (board[2][0] != 0) && (board[2][0] == board[1][1]) && (board[1][1] == board[0][2]) ) // Diagonal (/)	
		{
			hasWinner = true;
			if (board[2][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}

		if (hasWinner) displayResult(); 
		
		return hasWinner;
	}

	public static void displayResult() {
		if (p1Wins == true)
			System.out.println("Player1 wins!");
		else if (p2Wins == true)
			System.out.println("Player2 wins!");
		else 
			System.out.println("It's a draw. No winner.");
	}
}



