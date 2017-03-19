package Othello;

public class OthelloPlayer 
{
	private String name;
	private Piece color;
	private int numOfPiecesOwned;
	
	OthelloPlayer(String n, Piece c)
	{
		name = n;
		color = c;
		numOfPiecesOwned = 2;
	}
	
	public void addPiecesOwned(int numOfPieces)
	{
		numOfPiecesOwned += numOfPieces;
	}
	
	public int getPieces(){
		return numOfPiecesOwned;
	}
	
	public String getPlayerName()
	{
		return name;
	}
	
	public Piece getPlayerColor()
	{
		return color;
	}
}
