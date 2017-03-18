/* Author: Irish Marquez 
 * Class: Informatics 122 
 * Final Project (Client-Server Board Game Application)
 * Date: March 5, 2017
 * Description: TicTacToe Logic
 * */
package TicTacToe;
import java.util.Scanner;

public class Driver { 

	static int x = 3;	//grid
	static int y = 3; 	//grid
	static int[][] board = {{0,0,0},{0,0,0},{0,0,0}};

	//flag is used to check if all the positions are filled. may not be needed?
//	static int[][] flag = {{0,0,0},{0,0,0},{0,0,0}};	//keeps track of each position's fill status
	static int flagTotal = 0; // used to keep track of how many turns there has been 
	
	static Scanner scan;
	
	static int player1 = 1;	//value that is assigned to the player's occupied grid
	static int player2 = 2;
	static boolean p1sTurn = false;
	static boolean p2sTurn = false;
	static boolean p1Wins = false;
	static boolean p2Wins = false;
	
	static boolean hasWinner = false;
	
//	static int[] playersArray = {0,1};	// not sure if i need this
	
	static int x_Pos;	//occupied position
	static int y_Pos;	//occupied position
		
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
	
	public static void askForInput(int move, int turn) {
		//!!!check whether input is valid, num should only be 1 or 2
		/*if (num == 1) { 
			p1sTurn = true;	//player 1's turn	
			p2sTurn = false;
		}
		else {	
			p1sTurn = false;
			p2sTurn = true;		//player 2's turn	
		}
		System.out.println("Player " + num + "'s turn...");
		System.out.print("Enter row: ");
		x_Pos = scan.nextInt();
		System.out.print("Enter column: ");
		y_Pos = scan.nextInt();		*/
		
		//Checks whether position is already marked
		if (turn == 1) { 
			p1sTurn = true;	//player 1's turn	
			p2sTurn = false;
		}
		else {	
			p1sTurn = false;
			p2sTurn = true;		//player 2's turn	
		}
			switch (move){
			case 0:
				x_Pos = y_Pos = 1;
				break;
			case 1:
				x_Pos = 2;
				y_Pos = 1;
				break;
			case 2:
				x_Pos = 3;
				y_Pos = 1;
				break;
			case 3:
				x_Pos = 1;
				y_Pos = 2;
				break;
			case 4:
				x_Pos = y_Pos = 2;
				break;
			case 5:
				x_Pos = 3;
				y_Pos = 2;
				break;
			case 6:
				x_Pos = 1;
				y_Pos = 3;
				break;
			case 7:
				x_Pos = 2;
				y_Pos = 3;
				break;
			case 8:
				x_Pos = y_Pos = 3;
				break;
			}
	}
	
	public static void updateBoard() {
		// Not index-based
		if (p1sTurn == true) 
		{
			board[y_Pos-1][x_Pos-1] = 1;	
//			flag[x_Pos-1][y_Pos-1] = 1;		// may not need this
			++flagTotal;
		}

		else
		{
			board[y_Pos-1][x_Pos-1] = 2;
//			flag[x_Pos-1][y_Pos-1] = 1;		// may not need this		
			++flagTotal;
		}
	}
	

	
/*	public static void checkRows() {
		int i, j, total_row = 0;
		
		for(i = 0; i < x; ++i)
		{
			for(j = 0; j < y; ++j) 
			{
				total_row += board[i][j];
				flagTotal += flag[i][j];
			}

			
			if (flagTotal == 3 && total_row == 3) 	
			{
				p1Wins = true;
				hasWinner = true;
			}	
			else if (total_row == 6) 
			{
				p2Wins = true;
				hasWinner = true;
			}
			if (hasWinner == true) 
			{
				System.out.println("total_row: " + total_row);
				break;
			}
			if (j == y-1) total_row = 0;	//set to 0 for next round of checks 
		}
	}*/
	
/*	public static void checkColumns() {
		int i, j, total_column = 0;
		for(j = 0; j < y; ++j)
		{
			if (hasWinner == true) 
				break;		// should exit this loop
			
			for(i = 0; i < x; ++i) 
			{
				total_column += board[i][j];
				flagTotal += flag[i][j];
			}
			
			if (flagTotal == 3 && total_column == 3) 	
			{
				p1Wins = true;
				hasWinner = true;
			}	
			else if (total_column == 6) 
			{
				p2Wins = true;
				hasWinner = true;
			}
			if (hasWinner == true)
			{
				System.out.println("total_column: " + total_column);
				break;
			}	
			if (i == x-1) total_column = 0;	//set to 0 for next round of checks 
		}		
	}*/
	
/*	public static void checkDiagonals() {
		int i, j, total_tlbr = 0, total_trbl = 0;

		for (i = 0; i < x; ++i)
		{
			for (j = 0; j < y; ++j)
			{
				if (i == j)
					total_tlbr += board[i][j];
				if ((i+j) == 2)
					total_trbl += board[i][j];
			}
		}
		
		if (total_tlbr == 3)	//may need to check flag status first
		{
			p1Wins = true;
			hasWinner = true;
		}
		else if (total_trbl == 6) //may need to check flag status first
		{
			p2Wins = true;
			hasWinner = true;
		}
	}*/
	
	public static boolean checkForWin()  {	
		//CHECK ROWS
		System.out.println("Row 1:" +  board[1][0] + board[1][1] + board[1][2]);
		if ( (board[0][0] != 0) && (board[0][0] == board[0][1]) && (board[0][1] == board[0][2]) && (board[0][0] + board[0][1] + board[0][2] != 0) ) //row1
		{
			hasWinner = true;
			if (board[0][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
			//if (board[0][0] + board[0][1] + board[0][2] == 3) p1Wins = true;
			//else p2Wins = true;
		}
		else if ((board[1][0] != 0) && (board[1][0] == board[1][1]) && (board[1][1] == board[1][2])) //row2
		{	
			hasWinner = true;
			if (board[1][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
			//if (board[1][0] + board[1][1] + board[1][2] == 3) p1Wins = true;
			//else p2Wins = true;			
		}
		else if ( (board[2][0] != 0) && (board[2][0] == board[2][1]) && (board[2][1] == board[2][2]) && board[2][0] + board[2][1] + board[2][2] != 0 ) //row3
		{
			hasWinner = true;
			//if (board[2][0] + board[2][1] + board[2][2] == 3) p1Wins = true;
			//else p2Wins = true;
			if (board[2][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		// CHECK COLUMNS
		else if ( (board[0][0] != 0) && (board[0][0] == board[1][0]) && (board[1][0] == board[2][0]) && board[0][0] + board[1][0] + board[2][0] != 0 ) //col1			
		{
			hasWinner = true;
			//if (board[0][0] + board[1][0] + board[2][0] == 3) p1Wins = true;
			//else  p2Wins = true;	
			if (board[0][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		else if ( (board[0][1] != 0) && (board[0][1] == board[1][1]) && (board[1][1] == board[2][1]) && board[0][1] + board[1][1] + board[2][1] != 0 ) //col2			
		{
			hasWinner = true;
			//if (board[0][1] + board[1][1] + board[1][2] == 3) p1Wins = true;
			//else  p2Wins = true;
			if (board[0][1] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		else if ( (board[0][2] != 0) && (board[0][2] == board[1][2]) && (board[1][2] == board[2][2]) && board[0][2] + board[1][2] + board[1][2] != 0 ) //col3	
		{
			hasWinner = true;
			//if (board[0][2] + board[1][2] + board[1][2] == 3)  p1Wins = true;
			//else p2Wins = true;
			if (board[0][2] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}
		// CHECK DIAGONALS
		else if ( (board[0][0] != 0) &&(board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) // \
		{
			hasWinner = true;
			if (board[0][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
			//if (board[0][0] + board[1][1] + board[2][2] == 3) p1Wins = true;
			//else p2Wins = true;
		}
		else if ((board[2][0] != 0) && (board[2][0] == board[1][1]) && (board[1][1] == board[0][2]) && (board[2][0] + board[1][1] + board[0][2] != 0 )) // /	
		{
			hasWinner = true;
			//if (board[2][0] + board[1][1] + board[0][2] == 3) p1Wins = true;
			//else p2Wins = true;
			if (board[0][0] == 1){
				p1Wins = true;
			}else{
				p2Wins = true;
			}
		}

		if (hasWinner == true) displayResult(); 
		
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
	
	public static boolean isP1Wins() {
		return p1Wins;
	}
	
	public static boolean isP2Wins() {
		return p2Wins;
	}
	
	public static boolean isThereAWinner(){
		return hasWinner;
	}
	
	public static int getFlagTotal(){
		return flagTotal;
	}
	
	/*public static void main(String[] args) {
		scan = new Scanner(System.in);
	
		//initialize player and board 
//		createPlayers();
//		displayBoard();		

		do 
		{			
			askForInput(1);
			updateBoard();
			displayBoard();	
			if (flagTotal > 4) checkForWin();
			if (hasWinner == true) break;
	
			askForInput(2);	
			updateBoard();		
			displayBoard();	
			if (flagTotal > 4) checkForWin();	// can be optimized by checking only if there's been 6 turns
		} 
		while ( (hasWinner == false) || (flagTotal < 9) );	

		scan.close();
	}*/
}



