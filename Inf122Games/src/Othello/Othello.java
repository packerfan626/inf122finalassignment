package Othello;

import java.util.ArrayList;

import Client.Client;

public class Othello 
{
	OthelloPlayer player1, player2, currentPlayer, oppositePlayer, winner, loser;
	Piece[][] board;
	ArrayList<Integer> validMovesRow;
	ArrayList<Integer> validMovesColumn;
    final static int COLUMNS = 8;
    final static int ROWS = 8;
    OthelloGUI ogui;
    Piece temp;
    Client _client;
    public Boolean turn = false;
    
	public void initializeGame(Client client)
	{
		_client = client;
		System.out.println(_client.username + " " + _client.isHost);
		if(_client.isHost == true){
			player1 = new OthelloPlayer(_client.username, Piece.BLACK); // SAMPLE
			player2 = new OthelloPlayer("opponent", Piece.WHITE); // SAMPLE
			currentPlayer = player1;
			oppositePlayer = player2;
			turn = true;
		}else{
			player1 = new OthelloPlayer("opponent", Piece.BLACK); // SAMPLE
			player2 = new OthelloPlayer(_client.username, Piece.WHITE); // SAMPLE
			currentPlayer = player2;
			oppositePlayer = player1;
			turn = false;
		}

		board = new Piece[ROWS][COLUMNS];
		for(int i = 0; i < ROWS; i++)
			for(int j = 0; j < COLUMNS; j++)
				board[i][j] = Piece.EMPTY;
		board[3][3] = Piece.WHITE;
		board[3][4] = Piece.BLACK;
		board[4][3] = Piece.BLACK;
		board[4][4] = Piece.WHITE;
	}
	
	
	
	/*HERE: turn is a local variable here. If the player is the host (which is given FROM the client that is passed in). Then 
	 * then set the host as the first person to go by setting turn to true for host and turn to "false" for the joinee. 
	 * Call switchPlayer everytime a VALID move is sent (made) and recieved (made by opponent)
	 */
	public void switchPlayer(){
		if(turn = true){
			turn = false;
		}else{
			turn = true;
		}
		
	}
	
	public boolean checkForWin()
	{
		checkValidMoves(currentPlayer);
		if(validMovesRow.size() == 0 && validMovesColumn.size() == 0)
		{
			if(player1.getPieces() > player2.getPieces())
			{
				winner = player1;
				loser = player2;
			}
			else
			{
				winner = player2;
				loser = player1;
			}
			return true;
		}
		return false;
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
			Piece current = null; 
			
			if(p.equals(this.currentPlayer))
				this.currentPlayer.addPiecesOwned(1);
			else
				this.oppositePlayer.addPiecesOwned(1);
			
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
								
								if(p.equals(this.currentPlayer)){
									this.currentPlayer.addPiecesOwned(1);
									this.oppositePlayer.addPiecesOwned(-1);
								}
								else{
									this.currentPlayer.addPiecesOwned(-1);
									this.oppositePlayer.addPiecesOwned(1);
								}
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
			
			temp = current;
		}
		
		//Send move to other client		
		System.out.println(this.currentPlayer.getPlayerColor() + " has " + this.currentPlayer.getPieces() + " pieces");
		System.out.println(this.oppositePlayer.getPlayerColor() + " has " + this.oppositePlayer.getPieces() + " pieces");
	}
	
	public void reset()
	{
	    board = new Piece[ROWS][COLUMNS];
	    for(int i = 0; i < ROWS; i++)
	    {
	        for(int j = 0; j < COLUMNS; j++)
	        {
	            board[i][j] = Piece.EMPTY;
	        }
	    }
	    board[3][3] = Piece.WHITE;
	    board[3][4] = Piece.BLACK;
	    board[4][3] = Piece.BLACK;
	    board[4][4] = Piece.WHITE;
	}
	
}