package Othello;

import java.util.ArrayList;

public class Othello 
{
	OthelloPlayer player1;
	OthelloPlayer player2;
	OthelloPlayer currentPlayer;
	OthelloPlayer oppositePlayer;
	Piece[][] board;
	ArrayList<Integer> validMovesRow;
	ArrayList<Integer> validMovesColumn;
	ArrayList<String> directions;
	ArrayList<Integer> blackPieceException;
    final static int COLUMNS = 8;
    final static int ROWS = 8;
    
	public void initializeGame()
	{
		player1 = new OthelloPlayer("Chris", Piece.BLACK); // SAMPLE
		player2 = new OthelloPlayer("Janay", Piece.WHITE); // SAMPLE
		currentPlayer = player1;
		oppositePlayer = player2;
		board = new Piece[ROWS][COLUMNS];
		for(int i = 0; i < ROWS; i++)
			for(int j = 0; j < COLUMNS; j++)
				board[i][j] = Piece.EMPTY;
		board[3][3] = Piece.WHITE;
		board[3][4] = Piece.BLACK;
		board[4][3] = Piece.BLACK;
		board[4][4] = Piece.WHITE;
	}
	
	public void switchPlayer(){
		if(currentPlayer == player1){
			currentPlayer = player2;
			oppositePlayer = player1;
		}else{
			currentPlayer = player1;
			oppositePlayer = player2;
		}
	}
	
	public boolean isValid(int row, int column, OthelloPlayer p){
		checkValidMoves(p);
		boolean isValid = false;
		for(int i = 0; i < validMovesRow.size(); i++){
			if(validMovesRow.get(i) == row && validMovesColumn.get(i) == column){
				isValid = true;
			}
		}
		return isValid;
	}
	
	public void checkValidMoves(OthelloPlayer player)
	{
		validMovesRow = new ArrayList<Integer>();
		validMovesColumn = new ArrayList<Integer>();
		directions = new ArrayList<String>();
		blackPieceException = new ArrayList<Integer>();
		
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					if(board[i][j] == currentPlayer.getPlayerColor())
					{
						// Check all 8 directions from the current piece
						
						// 1. Diagonally Up Left
						int row = i - 1;
						int column = j - 1;
						int numPiecesTake = 0; // How many pieces the player can take using this direction (must be >=1 piece)
						
						while(row >= 0 && column >= 0)
						{
							
							if(board[row][column] == currentPlayer.getPlayerColor())
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY && numPiecesTake == 0)
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY)
							{
								
									if(numPiecesTake >= 1)
									{
										validMovesRow.add(row);
										validMovesColumn.add(column);
										directions.add("Diagonal Up Left");
										blackPieceException.add(numPiecesTake);
										break;
									}
								
							}
							row -= 1;
							column -= 1;
							numPiecesTake++;
						}
						
						// 2. Diagonally Up Right
						row = i - 1;
						column = j + 1;
						numPiecesTake = 0;
						while(row >= 0 && column < 8)
						{
							if(board[row][column] == oppositePlayer.getPlayerColor())
							{
								numPiecesTake = 0;
							}
							if(board[row][column] == currentPlayer.getPlayerColor())
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY && numPiecesTake == 0)
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY)
							{
								
									if(numPiecesTake >= 1)
									{
										validMovesRow.add(row);
										validMovesColumn.add(column);
										directions.add("Diagonal Up Right");
										blackPieceException.add(numPiecesTake);
										break;
									}
								
							}
							row -= 1;
							column += 1;
							numPiecesTake++;
						}
						
						// 3. Diagonally Down Right
						row = i + 1;
						column = j + 1;
						numPiecesTake = 0;
						while(row < 8 && column < 8)
						{
							if(board[row][column] == oppositePlayer.getPlayerColor())
							{
								numPiecesTake = 0;
							}
							if(board[row][column] == Piece.EMPTY && numPiecesTake == 0)
							{
								break;
							}
							if(board[row][column] == currentPlayer.getPlayerColor())
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY)
							{
								if(row - i >= 1) // If one or more pieces are white, then it's a valid move
								{
									if(numPiecesTake >= 1)
									{
										validMovesRow.add(row);
										validMovesColumn.add(column);
										directions.add("Diagonal Down Right");
										blackPieceException.add(numPiecesTake);
										break;
									}
								}
							}
							row += 1;
							column += 1;
							numPiecesTake++;
						}
						
						// 4. Diagonally Down Left
						row = i + 1;
						column = j - 1;
						numPiecesTake = 0;
						while(row < 8 && column >= 0)
						{
							if(board[row][column] == oppositePlayer.getPlayerColor())
							{
								numPiecesTake = 0;
							}
							if(board[row][column] == currentPlayer.getPlayerColor())
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY && numPiecesTake == 0)
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY)
							{
								if(row - i >= 1) // If one or more pieces are white, then it's a valid move
								{
									if(numPiecesTake >= 1)
									{
										validMovesRow.add(row);
										validMovesColumn.add(column);
										directions.add("Diagonal Down Left");
										blackPieceException.add(numPiecesTake);
										break;
									}
								}
							}
							row += 1;
							column -= 1;
							numPiecesTake++;
						}
						
						// 5. Up
						row = i - 1;
						column = j;
						numPiecesTake = 0;
						while(row >= 0)
						{
							if(board[row][column] == oppositePlayer.getPlayerColor())
							{
								numPiecesTake = 0;
							}
							if(board[row][column] == currentPlayer.getPlayerColor())
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY && numPiecesTake == 0)
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY)
							{
								if(i - row >= 1) // If one or more pieces are white, then it's a valid move
								{
									if(numPiecesTake >= 1)
									{
										validMovesRow.add(row);
										validMovesColumn.add(column);
										directions.add("Up");
										blackPieceException.add(numPiecesTake);
										break;
									}
								}
							}
							row -= 1;
							numPiecesTake++;
						}
						
						// 6. Down
						row = i + 1;
						column = j;
						numPiecesTake = 0;
						while(row < 8)
						{
							if(board[row][column] == oppositePlayer.getPlayerColor())
							{
								numPiecesTake = 0;
							}
							if(board[row][column] == currentPlayer.getPlayerColor())
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY && numPiecesTake == 0)
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY)
							{
								if(row - i >= 1) // If one or more pieces are white, then it's a valid move
								{
									if(numPiecesTake >= 1)
									{
										validMovesRow.add(row);
										validMovesColumn.add(column);
										directions.add("Down");
										blackPieceException.add(numPiecesTake);
										break;
									}
								}
							}
							row += 1;
							numPiecesTake++;
						}
						
						// 7. Left
						row = i;
						column = j - 1;
						numPiecesTake = 0;
						while(column >= 0)
						{
							if(board[row][column] == oppositePlayer.getPlayerColor())
							{
								numPiecesTake = 0;
							}
							if(board[row][column] == currentPlayer.getPlayerColor())
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY && numPiecesTake == 0)
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY)
							{
								if(j - column >= 1) // If one or more pieces are white, then it's a valid move
								{
									if(numPiecesTake >= 1)
									{
										validMovesRow.add(row);
										validMovesColumn.add(column);
										directions.add("Left");
										blackPieceException.add(numPiecesTake);
										break;
									}
								}
							}
							column -= 1;
							numPiecesTake++;
						}
						
						// 8. Right
						row = i;
						column = j + 1;
						numPiecesTake = 0;
						while(column < 8)
						{
							if(board[row][column] == oppositePlayer.getPlayerColor())
							{
								numPiecesTake = 0;
							}
							if(board[row][column] == currentPlayer.getPlayerColor())
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY && numPiecesTake == 0)
							{
								break;
							}
							if(board[row][column] == Piece.EMPTY)
							{
								
									if(numPiecesTake >= 1)
									{
										validMovesRow.add(row);
										validMovesColumn.add(column);
										directions.add("Right");
										blackPieceException.add(numPiecesTake);
										break;
									}
								
							}
							column += 1;
							numPiecesTake++;
						}
					}
				}
			}
		
	}
	
	public void makeMove(int row, int column, OthelloPlayer p)
	{
	
		System.out.println(p.getPlayerName() + ", make your move!");
		
		// PLAYER CHOOSES MOVE
		// GUI FLIPS TILES
		// ADD FLIPPED TILES TO TOTAL NUMBER OF PIECES OWNED FOR PLAYER
		
		
		if (board[row][column] == Piece.EMPTY)
		{
			int currentX;
			int currentY;
			
			boolean isEmpty;
			Piece current; 
			
			this.currentPlayer.addPiecesOwned(1);
			
			for (int x = -1; x <= 1; x++)
			{
				for (int y = -1; y <= 1; y++)
				{
					currentX = column + x;
					currentY = row + y;
					if(currentY == -1 || currentX == -1 || currentY == 8 || currentX == 8){
						continue;
					}
					
					current = board[currentY][currentX];
					isEmpty = false;
					
					
					if (current == Piece.EMPTY || current == p.getPlayerColor() )
					{
						continue;
					}

					while (!isEmpty)
					{
						currentX += x;
						currentY += y;
						
						if(currentY == -1 || currentX == -1 || currentY == 8 || currentX == 8){
							break;
						}
						
						current = board[currentY][currentX];

						if (current == p.getPlayerColor())
						{
							isEmpty = true;

							currentX -= x;
							currentY -= y;
							
							current = board[currentY][currentX];
							
							while(current != Piece.EMPTY)
							{
								board[currentY][currentX] = p.getPlayerColor();
								this.currentPlayer.addPiecesOwned(1);
								this.oppositePlayer.addPiecesOwned(-1);
								currentX -= x;
								currentY -= y;
								current = board[currentY][currentX];
							}
							
						}

						else if (current == Piece.EMPTY)
						{
							isEmpty = true;
						}
					}
				}
			}
		}
		
		System.out.println(this.currentPlayer.getPlayerColor() + " has " + this.currentPlayer.getPieces() + " pieces");
		System.out.println(this.oppositePlayer.getPlayerColor() + " has " + this.oppositePlayer.getPieces() + " pieces");

	}
	
	public void run()
	{
		for(int i = 0; i < this.ROWS; i++)
		{
			for(int j = 0; j < this.COLUMNS; j++)
			{
				System.out.print(board[i][j] + "   ");
			}
			System.out.println("");
		}
	}
}