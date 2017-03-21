package Games;

import javax.swing.JPanel;

import Server.Server.PlayerThread;

public abstract class Game implements Createable
{
	String player1;
	String player2;
	
	//METHODS For Battleship
	public void receiveUpdateBoard(boolean hasHit, int x, int y, String s, boolean des) { }
	public void receiveDeployStatus(boolean deployed){ }	

	
	//some functions that each game implements
	
	@Override
	public void makeBoard(int x, int y)
	{
		// TODO Auto-generated method stub
		
	}
}
